package club.multilab.music.core.service

import club.multilab.music.core.dto.intent.Intent
import club.multilab.music.core.dto.reply.Reply
import club.multilab.music.core.dto.reply.mapReply
import club.multilab.music.core.dto.track.Track
import club.multilab.music.core.source.MusicSource
import club.multilab.music.utils.log.Logger
import kotlinx.coroutines.flow.Flow

class MusicServiceImpl(private val source: MusicSource): MusicService, Logger {

    override fun logTag(): String = this::class.simpleName.orEmpty()

    override fun searchForTracks(searchForTracks: Intent.SearchForTracks): Flow<Reply<Intent.SearchForTracks.Result>> =
        source.searchForTracks(searchForTracks.searchQuery)
            .mapReply(
                lineCode = "3105231716",
                logger = this,
                "Error mapping SearchForTracks Intent Result for: $searchForTracks"
            ) { Intent.SearchForTracks.Result(it) }

    override fun getTrackMedia(getTrackMedia: Intent.GetTrackMedia): Flow<Reply<Track.Media>> = source
        .getTrackMedia(getTrackMedia.trackID)

    override fun <TReply> handleIntent(intent: Intent<TReply>): Flow<Reply<TReply>> = when (intent) {
        is Intent.SearchForTracks -> searchForTracks(intent)
        is Intent.GetTrackMedia -> getTrackMedia(intent)
    }.mapReply("2905231247", this, "Error cast reply type of intent $intent") {
        @Suppress("UNCHECKED_CAST")
        it as TReply
    }

}