import org.gradle.kotlin.dsl.android

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ktlint)
}

android {
    namespace = "com.tfandkusu.ga913android.testutil"
    compileSdk = libs.versions.compileSdk.get().toInt()
}

dependencies {
    implementation(libs.junit)
    implementation(libs.kotlinx.coroutines.test)
}
