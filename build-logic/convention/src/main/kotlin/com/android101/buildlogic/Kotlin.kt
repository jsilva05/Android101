package com.android101.buildlogic

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

internal fun Project.configureKotlin() {
    tasks.withType<KotlinCompile>().configureEach {
        compilerOptions {
            allWarningsAsErrors.set(true)
        }
    }

    dependencies {
        if (hasAndroidPlugin) {
            "implementation"(libs.findLibrary("coroutines.android").get())
        } else {
            "implementation"(libs.findLibrary("coroutines").get())
        }
    }
}
