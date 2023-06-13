package club.multilab.music.server

import club.multilab.music.core.di.CORE_MODULE
import club.multilab.music.data.di.DATA_MODULE
import club.multilab.music.server.adapter.MusicServiceAdapter
import club.multilab.music.utils.log.initLogger
import io.github.aakira.napier.DebugAntilog
import io.ktor.serialization.kotlinx.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import kotlinx.serialization.json.Json
import org.koin.ktor.ext.get
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger
import java.time.Duration

fun main(args: Array<String>) = io.ktor.server.netty.EngineMain.main(args)

// Called from application.conf
fun Application.module() {
    initLogger("MusicService/Server", DebugAntilog())
    install(Koin) {
        slf4jLogger()
        modules(CORE_MODULE, DATA_MODULE, serverModule)
    }
    install(ContentNegotiation) {
        json(this@module.get<Json>())
    }
    install(WebSockets) {
        contentConverter = KotlinxWebsocketSerializationConverter(this@module.get<Json>())
        pingPeriod = Duration.ofSeconds(15)
        timeout = Duration.ofSeconds(15)
        maxFrameSize = Long.MAX_VALUE
        masking = false
    }
    configureRouting()
}

fun Application.configureRouting() {
    routing {
        addAdapter(MusicServiceAdapter(get(), get()))
    }
}