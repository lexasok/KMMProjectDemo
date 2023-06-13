package club.multilab.music.server.adapter

import club.multilab.music.core.dto.reply.Reply
import kotlinx.coroutines.flow.Flow
import kotlin.time.Duration

sealed class HttpFlowAdapter<TParam, TReply> : RoutingAdapter() {

    abstract val path: String
    abstract val debounceTimeout: Duration
    abstract val flatMapWithBlock: (TParam) -> Flow<Reply<TReply>>
}