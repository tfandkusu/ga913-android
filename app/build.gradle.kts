plugins {
    alias(libs.plugins.ga913android.android.application.compose)
    alias(libs.plugins.ga913android.hilt)
    alias(libs.plugins.compose)
    alias(libs.plugins.androidx.navigation.safeargs)
    alias(libs.plugins.google.services)
}

android {
    namespace = "com.tfandkusu.ga913android"

    defaultConfig {
        applicationId = "com.tfandkusu.ga913android"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
}

hilt {
    enableAggregatingTask = false
}

dependencies {
    implementation(projects.model)
    implementation(projects.data)
    implementation(projects.viewCommon)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activity.compose)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.fragment.compose)
    implementation(libs.coil.compose)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.androidx.test.core)
    testImplementation(libs.mockk)
    testImplementation(libs.konsist)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    testImplementation(libs.junit)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics.ktx)
    implementation(libs.ga913.kmp)
}
