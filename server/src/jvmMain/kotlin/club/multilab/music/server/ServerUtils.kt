package club.multilab.music.server

import club.multilab.music.core.dto.reply.*
import club.multilab.music.server.adapter.RoutingAdapter
import club.multilab.music.utils.log.Logger
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.serialization.PolymorphicSerializer
import kotlinx.serialization.json.Json
import kotlin.time.Duration

@OptIn(FlowPreview::class)
suspend inline fun <reified TParam, reified TReply> WebSocketServerSession.flatMapWithFlowReply(
    logger: Logger,
    debounceTimeout: Duration = Duration.ZERO,
    json: Json,
    crossinline flatMapWith: (TParam) -> Flow<Reply<TReply>>
) {
    incoming
        .receiveAsFlow()
        .debounce(debounceTimeout)
        .mapToReplyOfDTO<TParam>(logger, json)
        .flatMapReply(flatMapWith)
        .collect { reply ->
            executeWithReply(
                lineCode = "2505231407",
                onErrorMessage = "Error send serialized: $reply",
                logger = logger,
            ) {
                logger.logI("Reply for ${TParam::class}: $reply")
                sendSerialized(reply)
            }
        }
}

@OptIn(FlowPreview::class)
suspend inline fun <reified TParam, reified TReply> WebSocketServerSession.flatMapWithFlowReplyAsAny(
    logger: Logger,
    debounceTimeout: Duration = Duration.ZERO,
    json: Json,
    crossinline flatMapWith: (TParam) -> Flow<Reply<TReply>>
) {
    incoming
        .receiveAsFlow()
        .debounce(debounceTimeout)
        .mapAsText(logger)
        .mapReply(lineCode = "0105231520", onErrorMessage = "Error mapping ${TParam::class} by type Any", logger = logger) { text ->
            logger.logI("Text: $text")
            json.decodeFromString(PolymorphicSerializer(Any::class), text)
        }
        .mapReply("0106231535", onErrorMessage = "Error casting Any to type: ${TParam::class}", logger = logger) {
            it as TParam
        }
        .flatMapReply(flatMapWith)
        .catch {
            it.printStackTrace()
            logger.logW("Error: $this, $it")
        }
        .collect { reply ->
            executeWithReply(
                lineCode = "2505231407",
                onErrorMessage = "Error send serialized: $reply",
                logger = logger,
            ) {
                logger.logI("Reply for ${TParam::class}: $reply")
                val replyStr = json.encodeToString(PolymorphicSerializer(Any::class), reply)
                send(replyStr)
            }
        }
}


@OptIn(FlowPreview::class)
inline fun <reified TParam : Any, reified TReply> Route.postAsFlow(
    path: String,
    logger: Logger,
    debounceTimeout: Duration,
    crossinline flatMapWithBlock: (TParam) -> Flow<Reply<TReply>>
) {
    route(path) {
        post<TParam> {
            flowOf(it)
                .debounce(debounceTimeout)
                .flatMapConcat { params ->
                    flatMapWithBlock(params)
                }.collect { reply ->
                    executeWithReply("2805231604", onErrorMessage = "Error respond $reply", logger = logger) {
                        logger.logI("Reply for ${TParam::class}: $reply")
                        call.respond(reply) }
                }
        }
    }
}

fun Route.addAdapter(adapter: RoutingAdapter) { adapter.initAdapter(this) }