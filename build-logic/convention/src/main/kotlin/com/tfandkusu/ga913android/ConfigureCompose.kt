package com.tfandkusu.ga913android

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        buildFeatures {
            compose = true
        }
        composeOptions {
            kotlinCompilerExtensionVersion = "1.5.1"
        }
    }
    dependencies {
        val bom = libs.findLibrary("androidx-compose-bom").get()
        add("implementation", platform(bom))
        add("implementation", libs.findLibrary("androidx-core-ktx").get())
        add("implementation", libs.findLibrary("androidx-lifecycle-runtime-ktx").get())
        add("implementation", libs.findLibrary("androidx-lifecycle-viewmodel-ktx").get())
        add("implementation", libs.findLibrary("androidx-ui").get())
        add("implementation", libs.findLibrary("androidx-ui-graphics").get())
        add("implementation", libs.findLibrary("androidx-ui-tooling-preview").get())
        add("implementation", libs.findLibrary("androidx-material3").get())
        add("implementation", libs.findLibrary("material-icons-core").get())
        add("implementation", libs.findLibrary("ga913-kmp").get())
        add("implementation", libs.findLibrary("coil-compose").get())
        add("implementation", libs.findLibrary("androidx-fragment-ktx").get())
        add("implementation", libs.findLibrary("androidx-navigation-fragment-ktx").get())
        add("implementation", libs.findLibrary("androidx-navigation-ui-ktx").get())
        add("implementation", libs.findLibrary("androidx-fragment-compose").get())
    }
}
