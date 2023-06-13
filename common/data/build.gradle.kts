plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization") version "1.8.0"
}

group = "club.multilab.music.data"
version = "0.0.0"

kotlin {
    jvm {
        jvmToolchain(11)
        withJava()
    }
    sourceSets {
        val commonMain by getting {
            dependencies {

                // Project modules
                implementation(project(Modules.Common.CORE))
                implementation(project(Modules.Common.UTILS))

                // Koin
                implementation(Deps.Koin.CORE)

                implementation(Deps.Napier.CORE)
                implementation(Deps.KotlinX.Coroutines.CORE)

                implementation(Deps.Ktor.Client.CORE)
                implementation(Deps.Ktor.Client.CIO)
                implementation(Deps.Ktor.Client.SERIALIZATION)
                implementation(Deps.Ktor.Client.LOGGING)
                implementation(Deps.Ktor.Client.NEGOTIATION)
                implementation(Deps.Ktor.Serialization.JSON)
            }
        }
    }
}