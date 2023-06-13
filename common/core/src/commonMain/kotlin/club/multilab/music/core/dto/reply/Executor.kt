package club.multilab.music.core.dto.reply

import club.multilab.music.core.dto.error.getLogMessage
import club.multilab.music.utils.log.Logger
import kotlin.experimental.ExperimentalTypeInference

@OptIn(ExperimentalTypeInference::class)
inline fun <T> executeWithReply(
    lineCode: String,
    onErrorMessage: String,
    logger: Logger,
    @BuilderInference block:() -> T
): Reply<T> =  try {
    Reply.Success(block())
} catch (t: Throwable) {
    t.printStackTrace()
    val error = ErrorValue(lineCode, onErrorMessage, t.message.orEmpty(), t.stackTraceToString())
    logger.logW(error.getLogMessage())
    Reply.Error(error)
}