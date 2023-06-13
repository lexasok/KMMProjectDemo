package club.multilab.music.client.model.root

import club.multilab.music.client.navigation.Screen
import club.multilab.music.core.dto.error.Error

data class RootState(
    val loading: Boolean = false,
    val error: Error = Error.EMPTY,
    // TODO set splash screen default
    val screen: Screen = Screen.SearchForTracks
)