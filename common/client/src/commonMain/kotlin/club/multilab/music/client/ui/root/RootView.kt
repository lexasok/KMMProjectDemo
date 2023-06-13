package club.multilab.music.client.ui.root

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import club.multilab.music.client.model.root.RootModel
import club.multilab.music.client.navigation.Router
import club.multilab.music.utils.log.Logger

private val LOGGER = Logger { "RootView" }

@Composable
fun RootView(model: RootModel, router: Router) {
    val state by model.state.collectAsState()
    LOGGER.logI("State: $state")
    router.resolveScreen(state.screen)
}