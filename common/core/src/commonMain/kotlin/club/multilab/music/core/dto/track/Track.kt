package club.multilab.music.core.dto.track

import kotlinx.serialization.Serializable

@Serializable
data class Track(
    val id: String,
    val name: String,
    val artist: String,
    val imageURL: String,
    val duration: String
) {

    fun isValid() = id.isNotEmpty()

    @Serializable
    data class Media(
        val trackID: String,
        val fileURL: String
    ) {

        fun isValid() = trackID.isNotEmpty()

        companion object {

            val EMPTY = Media("", "")
        }
    }

    companion object {

        val EMPTY = Track("", "", "", "", "")
    }
}