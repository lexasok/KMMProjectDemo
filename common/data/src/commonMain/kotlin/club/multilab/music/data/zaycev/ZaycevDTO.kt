package club.multilab.music.data.zaycev

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchResponse(
    @SerialName("trackIds")
    val trackIDs: List<Long>,
    @SerialName("tracksInfo")
    val info: Map<String, TrackInfoResponse>
)

@Serializable
data class TrackInfoResponse(
    @SerialName("track", )
    val name: String,
    @SerialName("duration")
    val duration: String,
    @SerialName("artistName")
    val artist: String,
    @SerialName("artistId")
    val artistID: Long,
    @SerialName("imageJpg")
    val imageURL: String = ""
)

@Serializable
data class TrackFilesRequest(
    @SerialName("trackIds")
    val trackIds: List<String>
)

@Serializable
data class TrackFilesResponse(
    @SerialName("tracks")
    val tracks: List<TrackFiles>
)

@Serializable
data class TrackFiles(
    @SerialName("id")
    val trackID: Long,
    @SerialName("streaming")
    val streaming: String,
    @SerialName("download")
    val download: String
)

@Serializable
data class TrackURLResponse(
    @SerialName("url")
    val url: String
)
