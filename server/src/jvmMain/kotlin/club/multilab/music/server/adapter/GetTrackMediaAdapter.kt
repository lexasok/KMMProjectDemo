package club.multilab.music.server.adapter

import club.multilab.music.core.dto.intent.Intent
import club.multilab.music.core.dto.reply.Reply
import club.multilab.music.core.dto.track.Track
import club.multilab.music.server.postAsFlow
import club.multilab.music.utils.log.Logger
import io.ktor.server.routing.*
import kotlinx.coroutines.flow.*
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.toDuration

class GetTrackMediaAdapter(
    override val flatMapWithBlock: (Intent.GetTrackMedia) -> Flow<Reply<Track.Media>>
) : HttpFlowAdapter<Intent.GetTrackMedia, Track.Media>(), Logger {

    override val path: String = "getTrackMedia"
    override val debounceTimeout: Duration = 0.toDuration(DurationUnit.SECONDS)

    override fun logTag(): String = this::class.simpleName.orEmpty()

    override fun initAdapter(route: Route) {
        route.postAsFlow<Intent.GetTrackMedia, Track.Media>(path = path, logger = this, debounceTimeout = debounceTimeout, flatMapWithBlock = flatMapWithBlock)
    }
}