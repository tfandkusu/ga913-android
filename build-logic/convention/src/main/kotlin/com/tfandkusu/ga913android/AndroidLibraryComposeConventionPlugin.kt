package com.tfandkusu.ga913android

import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibraryComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("ga913android.android.library")
                apply("org.jetbrains.kotlin.plugin.compose")
                apply("androidx.navigation.safeargs.kotlin")
            }
            extensions.configure<LibraryExtension> {
                configureAndroidCompose(this)
            }
        }
    }
}
