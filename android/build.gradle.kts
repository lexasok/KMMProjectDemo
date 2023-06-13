plugins {
    id("org.jetbrains.compose")
    id("com.android.application")
    kotlin("android")
}

group = "club.multilab.music.android"
version = "0.0.0"

dependencies {
    implementation(project(Modules.Common.CLIENT))
    implementation(project(Modules.Common.CORE))
    implementation(project(Modules.Common.UTILS))

    implementation(Deps.Android.AndroidX.COMPOSE_ACTIVITY)

    implementation(Deps.Koin.CORE)
    implementation(Deps.Koin.ANDROID)

    implementation(Deps.Napier.CORE)
}

android {
    compileSdk = 33
    defaultConfig {
        applicationId = "club.multilab.music.android"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0-SNAPSHOT"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}