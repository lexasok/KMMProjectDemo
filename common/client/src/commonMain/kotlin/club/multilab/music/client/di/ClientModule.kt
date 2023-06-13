package club.multilab.music.client.di

import club.multilab.music.client.model.root.RootModel
import club.multilab.music.client.model.searchfortracks.SearchForTracksModel
import club.multilab.music.client.navigation.Router
import club.multilab.music.client.navigation.RouterCommonImpl
import club.multilab.music.client.service.MusicServiceClientImpl
import club.multilab.music.client.ui.App
import club.multilab.music.client.ui.AppMaterial3Impl
import club.multilab.music.core.service.MusicService
import org.koin.dsl.module

val COMMON_CLIENT_MODULE = module {

    // ROOT
    single<RootModel> { RootModel() }
    single<Router> { RouterCommonImpl() }
    single<App> { AppMaterial3Impl() }
    single<MusicService> { MusicServiceClientImpl(get()) }

    // SEARCH
    single { SearchForTracksModel() }
}