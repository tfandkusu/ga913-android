plugins {
    alias(libs.plugins.ga913android.android.library)
    alias(libs.plugins.ga913android.hilt)
    alias(libs.plugins.kotlinx.serialization)
}

android {
    namespace = "com.tfandkusu.ga913android.data"
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
}
