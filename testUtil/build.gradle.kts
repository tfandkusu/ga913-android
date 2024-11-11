import org.gradle.kotlin.dsl.android

plugins {
    alias(libs.plugins.ga913android.test.util)
}

android {
    namespace = "com.tfandkusu.ga913android.testutil"
}
