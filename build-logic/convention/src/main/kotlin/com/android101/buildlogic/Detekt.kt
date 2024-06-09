package com.android101.buildlogic

import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureDetekt() {
    with(pluginManager) {
        apply("io.gitlab.arturbosch.detekt")
    }

    detekt {
        buildUponDefaultConfig = true
        autoCorrect = true
        config.setFrom("${rootProject.projectDir}/config/detekt/detekt.yml")
        parallel = true

        dependencies {
            "detektPlugins"(libs.findLibrary("detekt.formatting").get())
            "detektPlugins"(libs.findLibrary("detekt.compose").get())
        }
    }
}

private fun Project.detekt(action: DetektExtension.() -> Unit) =
    extensions.configure<DetektExtension>(action)
