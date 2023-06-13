package club.multilab.music.client.service

import club.multilab.music.core.dto.intent.Intent
import club.multilab.music.core.dto.reply.Reply
import club.multilab.music.core.dto.track.Track
import club.multilab.music.core.service.MusicService
import club.multilab.music.utils.log.Logger
import io.ktor.client.*
import kotlinx.coroutines.flow.Flow

private const val BASE_URL = "http://localhost:8080/"

class MusicServiceClientImpl(private val client: HttpClient) : MusicService, Logger {

    private val logger = this as Logger

    override fun logTag(): String = this::class.simpleName.orEmpty()

    override fun searchForTracks(searchForTracks: Intent.SearchForTracks): Flow<Reply<Intent.SearchForTracks.Result>> {
        TODO("Not yet implemented")
    }

    override fun getTrackMedia(getTrackMedia: Intent.GetTrackMedia): Flow<Reply<Track.Media>> {
        TODO("Not yet implemented")
    }

    override fun <TReply> handleIntent(intent: Intent<TReply>): Flow<Reply<TReply>> {
        TODO("Not yet implemented")
    }

}