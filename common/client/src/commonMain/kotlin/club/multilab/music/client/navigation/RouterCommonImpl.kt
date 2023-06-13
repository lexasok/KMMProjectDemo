package club.multilab.music.client.navigation

import androidx.compose.runtime.Composable
import club.multilab.music.client.ui.searchfortracks.SearchForTracksView
import club.multilab.music.utils.log.Logger
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

// TODO add navigation callback
class RouterCommonImpl : Router, KoinComponent, Logger {

    @Composable
    override fun resolveScreen(screen: Screen) {
        logI("Resolve screen: $screen")
        return when (screen) {
            Screen.SearchForTracks -> {
                SearchForTracksView(get())
            }
        }
    }

    override fun logTag(): String = this::class.simpleName.orEmpty()
}