package club.multilab.music.core.dto.error

import kotlinx.serialization.Serializable

@Serializable
data class Error (
    val code: String,
    val message: String,
    val exMessage: String,
    val stackTrace: String
) {

    companion object {

        val EMPTY = Error(code = "", message = "", exMessage = "", stackTrace = "")
    }
}

fun Error.getLogMessage() = "Error $code: $message\n$exMessage"