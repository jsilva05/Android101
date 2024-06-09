package com.android101.buildlogic

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureMoshi(
    codegen: Boolean,
    testsCodegen: Boolean,
) {
    with(pluginManager) {
        if (codegen || testsCodegen) {
            apply("com.google.devtools.ksp")
        }
    }

    dependencies {
        "implementation"(libs.findLibrary("moshi").get())
        "implementation"(libs.findLibrary("moshi.adapters").get())
        if (codegen) {
            "ksp"(libs.findLibrary("moshi.codegen").get())
        }
        if (testsCodegen) {
            "kspTest"(libs.findLibrary("moshi.codegen").get())
        }
    }
}
