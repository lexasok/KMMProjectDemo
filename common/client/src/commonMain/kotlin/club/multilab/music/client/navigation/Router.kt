package club.multilab.music.client.navigation

import androidx.compose.runtime.Composable

fun interface Router {

    @Composable
    fun resolveScreen(screen: Screen)
}