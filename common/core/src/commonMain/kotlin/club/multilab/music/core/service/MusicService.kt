package club.multilab.music.core.service

import club.multilab.music.core.dto.intent.Intent
import club.multilab.music.core.dto.reply.Reply
import club.multilab.music.core.dto.track.Track
import kotlinx.coroutines.flow.Flow

interface MusicService {

    fun searchForTracks(searchForTracks: Intent.SearchForTracks): Flow<Reply<Intent.SearchForTracks.Result>>

    fun getTrackMedia(getTrackMedia: Intent.GetTrackMedia): Flow<Reply<Track.Media>>

    fun <TReply> handleIntent(intent: Intent<TReply>): Flow<Reply<TReply>>
}