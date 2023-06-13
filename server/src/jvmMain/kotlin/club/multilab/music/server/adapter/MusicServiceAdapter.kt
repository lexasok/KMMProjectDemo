package club.multilab.music.server.adapter

import club.multilab.music.core.service.MusicService
import club.multilab.music.server.addAdapter
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json
import org.koin.core.component.KoinComponent

class MusicServiceAdapter(service: MusicService, private val json: Json) : RoutingAdapter(), KoinComponent, MusicService by service {

    override fun initAdapter(route: Route) {
        route.addAdapter(SearchForTracksAdapter(::searchForTracks, json = json))
        route.addAdapter(GetTrackMediaAdapter(::getTrackMedia))
        route.addAdapter(HandleIntentAdapter(::handleIntent, json))
    }
}