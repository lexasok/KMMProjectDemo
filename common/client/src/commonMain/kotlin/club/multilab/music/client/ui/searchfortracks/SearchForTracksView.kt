package club.multilab.music.client.ui.searchfortracks

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import club.multilab.music.client.model.searchfortracks.SearchForTracksModel
import club.multilab.music.utils.log.Logger

private val LOGGER = Logger { "SearchForTracksView" }

@Composable
fun SearchForTracksView(model: SearchForTracksModel) {
    val state by model.state.collectAsState()
    LOGGER.logI("State: $state")

    // PROTO TODO
    Text(text = "SearchForTracksScreen")
}