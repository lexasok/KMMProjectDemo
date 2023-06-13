package club.multilab.music.server

import club.multilab.music.core.dto.reply.Reply
import club.multilab.music.core.dto.reply.flatMap
import club.multilab.music.core.dto.reply.executeWithReply
import club.multilab.music.utils.log.Logger
import io.ktor.websocket.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

inline fun <reified T> Flow<Frame>.mapToReplyOfDTO(logger: Logger, json: Json = Json) =
    mapAsText(logger)
        .mapToReplyDTO<T>(logger, json)

fun Flow<Frame>.mapAsText(logger: Logger) = mapNotNull { it as? Frame.Text }
    .map {
        executeWithReply(
            "2205231349",
            "Error reading text from frame: $it",
            logger
        ) { it.readText() } }

inline fun <reified T> Flow<Reply<String>>.mapToReplyDTO(logger: Logger, json: Json = Json) = map { text ->
    text.flatMap {
        executeWithReply<T>(
            lineCode = "2205231350",
            onErrorMessage = "Error decoding JSON type: ${T::class.simpleName} from string: $it",
            logger
        )  { json.decodeFromString(it) }
    }
}