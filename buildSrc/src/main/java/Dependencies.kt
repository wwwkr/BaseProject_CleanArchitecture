object Versions {

    // AndroidX
    const val APP_COMPAT = "1.6.1"
    const val MATERIAL = "1.9.0"
    const val CONSTRAINT_LAYOUT = "2.1.4"

    // KTX
    const val CORE = "1.10.0"
    const val KTX_ACTIVITY = "1.7.2"
    const val KTX_FRAGMENT = "1.6.1"
    const val KTX_VIEWMODEL = "2.6.1"

    // TEST
    const val UNIT_TEST_JUNIT = "4.13.2"
    const val INTEGRATION_TEST_JUNIT = "1.1.5"

    // Android Test
    const val ESPRESSO_CORE = "3.5.1"

    // Hilt
    const val HILT = "2.44"

    // Network
    const val RETROFIT = "2.9.0"
    const val OKHTTP = "4.5.0"

    // Room
    const val ROOM = "2.5.2"

    // DataStore
    const val DATA_STORE = "1.0.0"

    // Firebase
    const val FIREBASE = "32.2.2"

    // Jetpack Navigation
    const val NAVIGATION = "2.3.5"

    // Glide
    const val GLIDE = "4.16.0"

    // Kotest
    const val KOTEST = "5.7.2"

    // Mockk
    const val MOCKK = "1.13.8"

    // SWIPERE
    const val SWIPE = "1.1.0"
}


object Libraries {

    object AndroidX {
        const val APP_COMPAT = "androidx.appcompat:appcompat:${Versions.APP_COMPAT}"
        const val MATERIAL = "com.google.android.material:material:${Versions.MATERIAL}"
        const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT}"
        const val FRAGMENT = "androidx.fragment:fragment:${Versions.KTX_FRAGMENT}"
        const val SWIPE = "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0"
    }

    object KTX {
        const val CORE = "androidx.core:core-ktx:${Versions.CORE}"
        const val KTX_ACTIVITY = "androidx.activity:activity-ktx:${Versions.KTX_ACTIVITY}"
        const val KTX_FRAGMENT = "androidx.fragment:fragment-ktx:${Versions.KTX_FRAGMENT}"
        const val KTX_VIEWMODEL = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.KTX_VIEWMODEL}"
    }

    object Test {
        const val JUNIT = "junit:junit:${Versions.UNIT_TEST_JUNIT}"
        const val KOTEST = "io.kotest:kotest-runner-junit5:${Versions.KOTEST}"
        const val KOTEST_ASSERTIONS = "io.kotest:kotest-assertions-core-jvm:${Versions.KOTEST}"
        const val MOCKK = "io.mockk:mockk:${Versions.MOCKK}"
        const val KOCKK_ANDROID = "io.mockk:mockk-android:${Versions.MOCKK}"
    }

    object AndroidTest {
        const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO_CORE}"
        const val JUNIT = "androidx.test.ext:junit:${Versions.INTEGRATION_TEST_JUNIT}"
    }

    object Hilt {
        const val HILT = "com.google.dagger:hilt-android:${Versions.HILT}"
        const val KAPT_HILT = "com.google.dagger:hilt-compiler:${Versions.HILT}"
    }

    object Room {
        const val KTX_ROOM = "androidx.room:room-ktx:${Versions.ROOM}"
        const val KAPT_ROOM = "androidx.room:room-compiler:${Versions.ROOM}"
        const val ROOM = "androidx.room:room-runtime:${Versions.ROOM}"
    }

    object Network {
        //Retrofit
        const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT}"
        const val RETROFIT_CONVERTER = "com.squareup.retrofit2:converter-gson:${Versions.RETROFIT}"

        //Okhttp
        const val OKHTTP = "com.squareup.okhttp3:okhttp:${Versions.OKHTTP}"
        const val OKHTTP_LOGGING_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:${Versions.OKHTTP}"
    }

    object DataStore {
        const val DATA_STORE = "androidx.datastore:datastore-preferences:${Versions.DATA_STORE}"
    }

    object Firebase {
        const val FIREBASE_BOM = "com.google.firebase:firebase-bom:${Versions.FIREBASE}"
        const val FIREBASE_CRASHLYTICS = "com.google.firebase:firebase-crashlytics-ktx"
        const val FIREBASE_ANALYTICS = "com.google.firebase:firebase-analytics-ktx"
    }

    object Jetpack {
        const val NAVIGATION = "androidx.navigation:navigation-fragment-ktx:${Versions.NAVIGATION}"
        const val NAVIGATION_UI = "androidx.navigation:navigation-ui-ktx:${Versions.NAVIGATION}"
    }

    object Glide {
        const val GLIDE = "com.github.bumptech.glide:glide:${Versions.GLIDE}"
    }
}