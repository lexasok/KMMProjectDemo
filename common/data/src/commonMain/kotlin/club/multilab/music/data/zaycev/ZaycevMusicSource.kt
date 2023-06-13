package club.multilab.music.data.zaycev

import club.multilab.music.core.dto.reply.*
import club.multilab.music.core.dto.search.SearchQuery
import club.multilab.music.core.dto.track.Track
import club.multilab.music.core.source.MusicSource
import club.multilab.music.utils.log.Logger
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

private const val BASE_URL = "https://zaycev.net/"

class ZaycevMusicSource(private val client: HttpClient) : MusicSource, Logger {

    override fun logTag() = this::class.simpleName.orEmpty()

    private val logger = this@ZaycevMusicSource as Logger

    override fun searchForTracks(query: SearchQuery): Flow<Reply<List<Track>>> =
        flow {
            val result = executeWithReply(
                lineCode = "2205231637",
                onErrorMessage = "Error getting search response for: $query",
                logger
            ) {
                 client.get("${BASE_URL}api/external/pages/search/tracks?q=${query.raw}&page=1&limit=20")
            }.map(
                lineCode = "2505231614",
                onErrorMessage = "Error body mapping to SearchResponse for: $query",
                logger = logger
            ) {
               it.body<SearchResponse>()
            }.flatMap {
                executeWithReply(
                    lineCode = " 2505231618",
                    onErrorMessage = "Error mapping track list for: $it",
                    logger = logger
                ) { mapTrackList(it, BASE_URL) }
            }
            emit(result)
        }

    override fun getTrackMedia(trackID: String): Flow<Reply<Track.Media>> =
        getPlayTrackURL(trackID)
            .mapReply("2505231619", logger, "Error mapping Track.Media for trackID: $trackID") {
                Track.Media(trackID, it)
            }



    @OptIn(FlowPreview::class)
    private fun getPlayTrackURL(trackID: String): Flow<Reply<String>> =
        getTrackStreamID(trackID)
            .flatMapConcat {
                when (it) {
                    is Reply.Error -> flowOf(it)
                    is Reply.Success -> getPlayTrackURLByStreamID(it.value)
                }
            }


    private fun getTrackStreamID(trackID: String): Flow<Reply<String>> =
        flow{
            emit(
                executeWithReply(
                    lineCode = "2205231646",
                    onErrorMessage = "Error getting stream ID for trackID: $trackID",
                    logger = logger
                ) {
                    client
                        .post(BASE_URL + "api/external/track/filezmeta") {
                            contentType(ContentType.Application.Json)
                            setBody(TrackFilesRequest(listOf(trackID)))
                        }.body<TrackFilesResponse>()

                }.map(
                    lineCode = "2505231624",
                    onErrorMessage = "Track stream not found for trackID: $trackID",
                    logger = logger
                ) {
                    it.tracks.find { files ->
                        files.trackID.toString() == trackID
                    }?.streaming ?: throw Throwable("Track stream not found for trackID: $trackID")
                }
            )
        }


    private fun getPlayTrackURLByStreamID(streamID: String): Flow<Reply<String>> =
        flow {
            emit(
                executeWithReply(
                    lineCode = "2205231645",
                    onErrorMessage = "Error getting play track URL for streamID: $streamID",
                    logger = logger
                ) {
                    client
                        .get("${BASE_URL}api/external/track/play/$streamID")
                        .body<TrackURLResponse>()
                }.map(
                    lineCode = "2505231635",
                    onErrorMessage = "Error body mapping to TrackURLResponse",
                    logger = logger
                ) {
                    it.url
                }
            )
        }
}