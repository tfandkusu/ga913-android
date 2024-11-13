package com.tfandkusu.ga913android

import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class TestUtilConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
                apply("org.jlleitschuh.gradle.ktlint")
            }
            dependencies {
                add("implementation", libs.findLibrary("junit").get())
                add("implementation", libs.findLibrary("kotlinx-coroutines-test").get())
            }
            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
            }
        }
    }
}
