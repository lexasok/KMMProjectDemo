package club.multilab.music.server.adapter

import io.ktor.server.routing.*
import kotlinx.serialization.json.Json

sealed class RoutingAdapter {

    abstract fun initAdapter(route: Route)
}



