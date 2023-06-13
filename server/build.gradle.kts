plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization") version "1.8.0"
    application
}

group = "club.multilab.music.server"
version = "0.0.0"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven")
}

kotlin {
    jvm {
        jvmToolchain(11)
        withJava()
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }

    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(project(Modules.Common.CORE))
                implementation(project(Modules.Common.DATA))
                implementation(project(Modules.Common.UTILS))

                implementation(Deps.Napier.CORE)

                // Server
                implementation(Deps.Ktor.Server.CORE)
                implementation(Deps.Ktor.Serialization.JSON)
                implementation(Deps.Ktor.Server.NETTY)
                implementation(Deps.Ktor.Server.WEB_SOCKETS)
                implementation(Deps.Ktor.Server.NEGOTIATION)

                // Logback
                implementation(Deps.Ktor.LOGBACK)

                // Client
                implementation(Deps.Ktor.Client.CORE)
                implementation(Deps.Ktor.Client.CIO)
                implementation(Deps.Ktor.Client.SERIALIZATION)
                implementation(Deps.Ktor.Client.LOGGING)
                implementation(Deps.Ktor.Client.NEGOTIATION)
                implementation(Deps.Ktor.Client.WEB_SOCKETS)

                // Coroutines
                implementation(Deps.KotlinX.Coroutines.CORE)

                // Naiper
                implementation(Deps.Napier.CORE)

                // Koin
                implementation(Deps.Koin.CORE)
                implementation(Deps.Koin.KTOR)
                implementation(Deps.Koin.LOGGER)
            }
        }

        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}

application {
    mainClass.set("club.multilab.music.server.ServerKt")
}