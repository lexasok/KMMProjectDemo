package club.multilab.music.server.adapter

import club.multilab.music.core.dto.intent.Intent
import club.multilab.music.core.dto.reply.Reply
import club.multilab.music.server.flatMapWithFlowReplyAsAny
import club.multilab.music.utils.log.Logger
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.json.Json
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.toDuration

class HandleIntentAdapter(
    override val flatMapWithBlock: (Intent<Any>) -> Flow<Reply<Any>>,
    private val json: Json
) : SocketFlowAdapter<Intent<Any>, Any>(), Logger {

    override val path: String = "/handleIntent"
    override val debounceTimeout: Duration = 0.toDuration(DurationUnit.SECONDS)

    override fun logTag(): String = this::class.simpleName.orEmpty()

    override fun initAdapter(route: Route) {
        route.webSocket(path) {
            flatMapWithFlowReplyAsAny<Intent<Any>, Any>(logger = this@HandleIntentAdapter, debounceTimeout = debounceTimeout, json, flatMapWith = flatMapWithBlock)
        }
    }
}