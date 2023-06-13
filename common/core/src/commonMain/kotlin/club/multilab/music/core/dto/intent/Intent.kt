package club.multilab.music.core.dto.intent

import club.multilab.music.core.dto.search.SearchQuery
import club.multilab.music.core.dto.track.Track
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("Intent")
sealed class Intent<TReply> {

    @Serializable
    @SerialName("Intent.SearchForTracks")
    data class SearchForTracks(
        val searchQuery: SearchQuery,
    ): Intent<SearchForTracks.Result>() {

        @Serializable
        @SerialName("Intent.SearchForTracks.Result")
        data class Result(
            val tracks: List<Track>
        )
    }

    @Serializable
    @SerialName("Intent.GetTrackMedia")
    data class GetTrackMedia(
        val trackID: String
    ) : Intent<Track.Media>()
}

