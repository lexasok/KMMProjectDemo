plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization") version "1.8.0"
}

group = "club.multilab.music.core"
version = "0.0.0"

kotlin {
    jvm()
    sourceSets {
        val commonMain by getting {
            dependencies {

                implementation(project(Modules.Common.UTILS))

                implementation(Deps.Koin.CORE)

                implementation(Deps.KotlinX.Coroutines.CORE)
                implementation(Deps.KotlinX.Serialization.CORE)
            }
        }
    }
}