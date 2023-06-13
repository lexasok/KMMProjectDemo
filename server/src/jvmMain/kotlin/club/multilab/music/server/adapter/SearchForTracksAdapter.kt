package club.multilab.music.server.adapter

import club.multilab.music.core.dto.intent.Intent
import club.multilab.music.core.dto.reply.Reply
import club.multilab.music.server.flatMapWithFlowReply
import club.multilab.music.utils.log.Logger
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.json.Json
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.toDuration

class SearchForTracksAdapter(
    override val flatMapWithBlock: (Intent.SearchForTracks) -> Flow<Reply<Intent.SearchForTracks.Result>>,
    private val json: Json
) : SocketFlowAdapter<Intent.SearchForTracks, Intent.SearchForTracks.Result>(), Logger {

    override val path: String = "/searchForTracks"
    override val debounceTimeout: Duration = 0.toDuration(DurationUnit.SECONDS)

    override fun logTag(): String = this::class.simpleName.orEmpty()

    override fun initAdapter(route: Route) {
        route.webSocket(path) {
            flatMapWithFlowReply<Intent.SearchForTracks, Intent.SearchForTracks.Result>(
                logger = this@SearchForTracksAdapter,
                debounceTimeout = debounceTimeout,
                json = json,
                flatMapWith = flatMapWithBlock
            )
        }
    }
}