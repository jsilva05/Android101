package com.android101.buildlogic

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureHilt() {
    with(pluginManager) {
        apply("com.google.devtools.ksp")

        if (hasAndroidPlugin) {
            apply("dagger.hilt.android.plugin")
        }
    }

    dependencies {
        if (hasAndroidPlugin) {
            "implementation"(libs.findLibrary("hilt.android").get())
            "kspAndroidTest"(libs.findLibrary("hilt.compiler").get())
        } else {
            "implementation"(libs.findLibrary("hilt").get())
        }
        "ksp"(libs.findLibrary("hilt.compiler").get())
        "kspTest"(libs.findLibrary("hilt.compiler").get())
    }
}
