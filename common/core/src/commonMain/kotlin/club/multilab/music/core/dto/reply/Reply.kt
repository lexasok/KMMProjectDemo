package club.multilab.music.core.dto.reply

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

typealias ErrorValue = club.multilab.music.core.dto.error.Error

@Serializable
@SerialName("Reply")
sealed class Reply<out T> {

    @Serializable
    @SerialName("Reply.Success")
    class Success<out T>(val value: T) : Reply<T>()

    @Serializable
    @SerialName("Reply.Error")
    class Error (val value: ErrorValue) : Reply<Nothing>()
}