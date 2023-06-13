package club.multilab.music.core.di

import club.multilab.music.core.service.MusicService
import club.multilab.music.core.service.MusicServiceImpl
import org.koin.dsl.module

val CORE_MODULE = module {
    single<MusicService> { MusicServiceImpl(get()) }
}