pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }

    // TODO use Deps
    plugins {
        kotlin("multiplatform").version(extra["kotlin.version"] as String)
        kotlin("android").version(extra["kotlin.version"] as String)
        id("com.android.application").version(extra["agp.version"] as String)
        id("com.android.library").version(extra["agp.version"] as String)
        id("org.jetbrains.compose").version(extra["compose.version"] as String)
    }
}

rootProject.name = "MusicService"


object Test {
    val a = 1
}

include(":android", ":desktop",  ":server", ":web", ":common:core", ":common:client", ":common:utils", ":common:data")
//include(Modules.ANDROID, Modules.DESKTOP, Modules.WEB, Modules.SERVER, Modules.Common.CORE, Modules.Common.CLIENT, Modules.Common.UTILS, Modules.Common.DATA)

