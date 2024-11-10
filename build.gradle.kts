// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.ktlint) apply false
    alias(libs.plugins.compose) apply false
    alias(libs.plugins.google.services) apply false
    alias(libs.plugins.detekt.gradle.plugin)
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.devtools.ksp) apply false
    alias(libs.plugins.dagger.hilt) apply false
}

subprojects {
    afterEvaluate {
        // detekt の設定方法はこちらを参考にした。
        // https://medium.com/@emrekoc/detekt-static-code-analyzer-for-android-c4c9bfd42a8c
        plugins.apply(libs.plugins.detekt.gradle.plugin.get().pluginId)
        // detekt カスタムルールの設定方法はこちらを参考にした
        // https://medium.com/@emrekoc/detekt-for-android-how-to-make-your-own-custom-rule-2861eb60e4ba
        dependencies {
            detektPlugins(project(":detekt-extensions"))
        }
        detekt {
            config.setFrom(file("$rootDir/config/detekt/detekt.yml"))
            buildUponDefaultConfig = true
            // 警告があってもエラーにしない
            ignoreFailures = true
        }
    }
}
