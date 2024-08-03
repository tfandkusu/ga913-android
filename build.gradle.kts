import com.diffplug.gradle.spotless.SpotlessExtension

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.spotless) apply false
}

subprojects {
    afterEvaluate {
        apply(
            plugin = libs.plugins.spotless.get().pluginId
        )
        configure<SpotlessExtension> {
            kotlin {
                target("**/*.kt")
                ktlint("1.3.1").setEditorConfigPath(
                    rootProject.file(".editorconfig").absolutePath
                )
            }
        }
    }
}
