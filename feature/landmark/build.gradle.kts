plugins {
    alias(libs.plugins.ga913android.android.library.compose)
    alias(libs.plugins.ga913android.hilt)
}

android {
    namespace = "com.tfandkusu.ga913android.landmark"
}

dependencies {
    implementation(projects.viewCommon)
    implementation(projects.model)
    implementation(projects.data)
    implementation(libs.kotlinx.coroutines.android)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.androidx.test.core)
    testImplementation(libs.mockk)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
