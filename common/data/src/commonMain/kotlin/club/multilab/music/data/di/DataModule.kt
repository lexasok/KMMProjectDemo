package club.multilab.music.data.di

import club.multilab.music.core.source.MusicSource
import club.multilab.music.data.client.JSON
import club.multilab.music.data.client.getHttpClient
import club.multilab.music.data.zaycev.ZaycevMusicSource
import io.ktor.client.*
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val DATA_MODULE = module {
    single<Json> { JSON }
    single<HttpClient> { getHttpClient(get()) }
    single<MusicSource> { ZaycevMusicSource(get()) }
}