plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.wwwkr.baseproject_cleanarchitecture"
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        applicationId = "com.wwwkr.baseproject_cleanarchitecture"
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            buildConfigField("boolean", "IS_DEV", "false")
        }

        debug {
            buildConfigField("boolean", "IS_DEV", "true")
        }

    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
        buildConfig = true
    }

}

dependencies {

    // Module
    implementation(project(":data"))
    implementation(project(":domain"))

    // AndroidX
    implementation(Libraries.AndroidX.APP_COMPAT)
    implementation(Libraries.AndroidX.MATERIAL)
    implementation(Libraries.AndroidX.CONSTRAINT_LAYOUT)

    // KTX
    implementation(Libraries.KTX.CORE)
    implementation(Libraries.KTX.KTX_ACTIVITY)
    implementation(Libraries.KTX.KTX_FRAGMENT)
    implementation(Libraries.KTX.KTX_VIEWMODEL)

    // TEST
    testImplementation(Libraries.Test.JUNIT)

    // AndroidTest
    androidTestImplementation(Libraries.AndroidTest.ESPRESSO_CORE)
    androidTestImplementation(Libraries.AndroidTest.JUNIT)

    // Hilt
    implementation(Libraries.Hilt.HILT)
    kapt(Libraries.Hilt.KAPT_HILT)

    // Room
    implementation(Libraries.Room.ROOM)
    implementation(Libraries.Room.KTX_ROOM)
    kapt(Libraries.Room.KAPT_ROOM)

    // Retrofit
    implementation(Libraries.Network.RETROFIT)
    implementation(Libraries.Network.RETROFIT_CONVERTER)

    // Okhttp
    implementation(Libraries.Network.OKHTTP)
    implementation(Libraries.Network.OKHTTP_LOGGING_INTERCEPTOR)

    // DataStore
    implementation(Libraries.DataStore.DATA_STORE)

    // Jetpack
    implementation(Libraries.Jetpack.NAVIGATION)
    implementation(Libraries.Jetpack.NAVIGATION_UI)

    // Glide
    implementation(Libraries.Glide.GLIDE)
}