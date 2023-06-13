object Deps {

    object Koin {

        private const val VERSION = "3.2.0"
        const val CORE = "io.insert-koin:koin-core:$VERSION"
        const val KTOR = "io.insert-koin:koin-ktor:$VERSION"
        const val LOGGER = "io.insert-koin:koin-logger-slf4j:$VERSION"
        const val TEST = "io.insert-koin:koin-test:$$VERSION"
        const val ANDROID = "io.insert-koin:koin-android:$VERSION"
    }

    object Ktor {

        private const val VERSION = "2.3.0"
        private const val LOGBACK_VERSION = "1.2.11"
        const val LOGBACK = "ch.qos.logback:logback-classic:$LOGBACK_VERSION"

        object Serialization {

            const val JSON = "io.ktor:ktor-serialization-kotlinx-json:$VERSION"
        }

        object Server {

            const val CORE = "io.ktor:ktor-server-core:$VERSION"
            const val NETTY = "io.ktor:ktor-server-netty:$VERSION"
            const val CIO = "io.ktor:ktor-server-cio-jvm:$VERSION"
            const val WEB_SOCKETS = "io.ktor:ktor-server-websockets-jvm:$VERSION"
            const val NEGOTIATION = "io.ktor:ktor-server-content-negotiation-jvm:$VERSION"
        }

        object Client {
            const val CORE = "io.ktor:ktor-client-core:$VERSION"
            const val SERIALIZATION = "io.ktor:ktor-client-serialization:$VERSION"
            const val LOGGING = "io.ktor:ktor-client-logging:$VERSION"
            const val NEGOTIATION = "io.ktor:ktor-client-content-negotiation:$VERSION"
            const val CIO = "io.ktor:ktor-client-cio:$VERSION"
            const val WEB_SOCKETS = "io.ktor:ktor-client-websockets:$VERSION"
        }
    }

    object Napier {

        private const val VERSION = "2.6.1"
        const val CORE = "io.github.aakira:napier:$VERSION"
    }

    object KotlinX {

        object Coroutines {

            private const val VERSION = "1.6.4"
            const val CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$VERSION"
        }

        object Serialization {

            private const val VERSION = "1.5.0"
            const val CORE = "org.jetbrains.kotlinx:kotlinx-serialization-core:$VERSION"
        }
    }

    object Android {

        object AndroidX {

            private const val VERSION = "1.7.1"
            const val COMPOSE_ACTIVITY = "androidx.activity:activity-compose:$VERSION"
        }
    }
}