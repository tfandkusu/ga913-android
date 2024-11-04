package com.tfandkusu.ga913android

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class HiltConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.google.devtools.ksp")
            pluginManager.apply("com.google.dagger.hilt.android")
            dependencies {
                add("ksp", libs.findLibrary("hilt-android-compiler").get())
                add("implementation", libs.findLibrary("dagger-hilt-android").get())
            }
        }
    }
}
