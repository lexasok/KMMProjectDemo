package club.multilab.music.server.adapter

import io.ktor.server.routing.*

sealed class RoutingAdapter {

    abstract fun initAdapter(route: Route)
}



