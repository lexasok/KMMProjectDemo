plugins {
    kotlin("multiplatform") version "1.8.0"
    id("org.jetbrains.compose")
    id("com.android.library")
}

group = "club.multilab.music.client"
version = "0.0.0"

kotlin {
    android()
    jvm("desktop") {
        jvmToolchain(11)
    }
    sourceSets {
        val commonMain by getting {
            @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
            dependencies {

                implementation(project(Modules.Common.CORE))
                implementation(project(Modules.Common.UTILS))
                implementation(project(Modules.Common.DATA))

                implementation(Deps.Koin.CORE)

                implementation(Deps.Napier.CORE)
                implementation(Deps.KotlinX.Coroutines.CORE)

                implementation(Deps.Ktor.Client.CORE)
                implementation(Deps.Ktor.Client.CIO)
                implementation(Deps.Ktor.Client.SERIALIZATION)
                implementation(Deps.Ktor.Client.LOGGING)
                implementation(Deps.Ktor.Client.NEGOTIATION)
                implementation(Deps.Ktor.Serialization.JSON)


                api(compose.runtime)
                api(compose.foundation)
                api(compose.material3)
                api(compose.preview)
            }
        }
        val androidMain by getting
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {

        kotlinOptions.jvmTarget = "11"
    }
}

android {
    compileSdk = 33
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 24
        targetSdk = 33
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}