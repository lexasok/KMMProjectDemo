plugins {
    kotlin("multiplatform") version "1.8.0"
}

group = "club.multilab.music.utils"
version = "0.0.0"

kotlin {
    jvm()
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Deps.Napier.CORE)
            }
        }
    }
}