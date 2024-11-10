plugins {
    alias(libs.plugins.ga913android.android.library.compose)
    alias(libs.plugins.ga913android.hilt)
}

android {
    namespace = "com.tfandkusu.ga913android.viewcommon"
}

dependencies {
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics.ktx)
    implementation(libs.ga913.kmp)
}
