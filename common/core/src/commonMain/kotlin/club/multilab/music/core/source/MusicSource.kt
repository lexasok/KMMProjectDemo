package club.multilab.music.core.source

import club.multilab.music.core.dto.reply.Reply
import club.multilab.music.core.dto.search.SearchQuery
import club.multilab.music.core.dto.track.Track
import kotlinx.coroutines.flow.Flow

interface MusicSource  {

    fun searchForTracks(query: SearchQuery): Flow<Reply<List<Track>>>

    fun getTrackMedia(trackID: String): Flow<Reply<Track.Media>>
}

