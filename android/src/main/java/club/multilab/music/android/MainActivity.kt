package club.multilab.music.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import club.multilab.music.client.di.COMMON_CLIENT_MODULE
import club.multilab.music.client.ui.App
import club.multilab.music.utils.log.initLogger
import io.github.aakira.napier.DebugAntilog
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.context.startKoin

class MainActivity : ComponentActivity(), KoinComponent {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        setContent { get<App>().Content() }
    }

    private fun init() {
        initLogger("Android", DebugAntilog())
        startKoin {
            androidContext(applicationContext)
            modules(COMMON_CLIENT_MODULE)
        }
    }
}