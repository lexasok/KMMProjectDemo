package club.multilab.music.core.dto.reply

import club.multilab.music.core.dto.error.Error
import club.multilab.music.core.dto.error.getLogMessage
import club.multilab.music.utils.log.Logger
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

inline fun <T, K> Reply<T>.flatMap(
    mapOnError: (ErrorValue) -> Reply<K> = { Reply.Error(it) },
    mapOnSuccess: (T) -> Reply<K>
): Reply<K> = when (this) {
    is Reply.Error -> mapOnError(this.value)
    is Reply.Success -> mapOnSuccess(value)
}

inline fun <T, K> Reply<T>.map(
    lineCode: String,
    onErrorMessage: String,
    logger: Logger,
    mapOnError: (ErrorValue) -> Reply<K> = { Reply.Error(it) },
    mapOnSuccess: (T) -> K
): Reply<K> = when (this) {
    is Reply.Error -> mapOnError(this.value)
    is Reply.Success -> executeWithReply(lineCode, onErrorMessage, logger) { mapOnSuccess(this.value) }
}

inline fun <T, K> Flow<Reply<T>>.mapReply(
    lineCode: String,
    logger: Logger,
    onErrorMessage: String,
    crossinline mapOnError: (ErrorValue) -> Reply<K> = { Reply.Error(it) },
    crossinline mapOnSuccess: (T) -> K
): Flow<Reply<K>> = map { reply ->
    reply.map(lineCode, onErrorMessage, logger, mapOnError, mapOnSuccess)
}

@OptIn(FlowPreview::class)
inline fun <T, K> Flow<Reply<T>>.flatMapReply(
    crossinline flatMapWith: (T) -> Flow<Reply<K>>,
): Flow<Reply<K>> = flatMapConcat { reply ->
    when (reply) {
        is Reply.Error -> flowOf(reply)
        is Reply.Success -> flatMapWith(reply.value)
    }
}

inline fun <T, K> Reply<T>.onErrorReturn(block: (Error) -> Reply<K>)  = when (this) {
    is Reply.Error -> block(this.value)
    is Reply.Success -> this
}

inline fun <T> Reply<T>.handle(
    logger: Logger,
    onError: (Error) -> Unit = { },
    onSuccess: (T) -> Unit
): Unit = when (this) {
    is Reply.Error -> {
        logger.logW(this.value.getLogMessage())
        onError(this.value)
    }
    is Reply.Success -> {
        logger.logI("Success: ${this.value}")
        onSuccess(value)
    }
}