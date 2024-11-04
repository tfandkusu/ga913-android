package com.tfandkusu.ga913android

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidLibraryConventionPlugin : Plugin<Project>  {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.library")
            pluginManager.apply("org.jlleitschuh.gradle.ktlint")
            dependencies {
                add("testImplementation", libs.findLibrary("junit").get())
            }
        }
    }
}