package com.tfandkusu.ga913android
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("ga913android.android.library.compose")
                apply("ga913android.hilt")
            }
            dependencies {
                add("implementation", project(":viewCommon"))
                add("implementation", project(":model"))
                add("implementation", project(":data"))
                add("implementation", libs.findLibrary("kotlinx-coroutines-android").get())
                add("debugImplementation", libs.findLibrary("androidx-ui-tooling").get())
                add("debugImplementation", libs.findLibrary("androidx-ui-test-manifest").get())
                add("testImplementation", project(":testUtil"))
                add("testImplementation", libs.findLibrary("kotlinx-coroutines-test").get())
                add("testImplementation", libs.findLibrary("androidx-test-core").get())
                add("testImplementation", libs.findLibrary("mockk").get())
            }
        }
    }
}
