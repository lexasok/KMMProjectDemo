package club.multilab.music.data.client

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

fun getHttpClient(json: Json): HttpClient = HttpClient(CIO) {
    install(Logging) { level = LogLevel.ALL }
    install(ContentNegotiation) { json(json) }
    install(HttpTimeout) {
        requestTimeoutMillis = 10000    // full request process timeout
        connectTimeoutMillis = 5000     // connection setup timeout
        //NOTE: docs mentioned: iOS doesn't support a connection timeout.
    }
}