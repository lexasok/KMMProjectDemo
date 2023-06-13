package club.multilab.music.client.ui

import androidx.compose.runtime.Composable
import club.multilab.music.client.ui.root.RootView
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

fun interface App {

    @Composable
    fun Content()
}

class AppMaterial3Impl : App, KoinComponent {

    @Composable
    override fun Content() = Material3App { RootView(get(), get()) }
}