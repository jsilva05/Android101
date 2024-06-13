package com.android101.buildlogic

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureLogger() {
    dependencies {
        "implementation"(libs.findLibrary("kermit").get())
    }
}
