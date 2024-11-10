import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "com.tfandkusu.ga913android.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_11
    }
}
dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
    compileOnly(libs.ksp.gradle.plugin)
    compileOnly(libs.compose.gradle.plugin)
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("hilt") {
            id = "ga913android.hilt"
            implementationClass = "com.tfandkusu.ga913android.HiltConventionPlugin"
        }
        register("androidLibrary") {
            id = "ga913android.android.library"
            implementationClass = "com.tfandkusu.ga913android.AndroidLibraryConventionPlugin"
        }
        register("androidApplication") {
            id = "ga913android.android.application"
            implementationClass = "com.tfandkusu.ga913android.AndroidApplicationConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "ga913android.android.library.compose"
            implementationClass = "com.tfandkusu.ga913android.AndroidLibraryComposeConventionPlugin"
        }
    }
}
