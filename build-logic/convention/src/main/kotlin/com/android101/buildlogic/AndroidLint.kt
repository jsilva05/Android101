package com.android101.buildlogic

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import com.android.build.api.dsl.Lint
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

internal fun Project.configureAndroidLint() {
    when {
        pluginManager.hasPlugin("com.android.application") ->
            configure<ApplicationExtension> {
                lint(Lint::configure)
            }

        pluginManager.hasPlugin("com.android.library") ->
            configure<LibraryExtension> {
                lint(Lint::configure)
            }

        else -> {
            pluginManager.apply("com.android.lint")
            configure<Lint>(Lint::configure)
        }
    }
}

private fun Lint.configure() {
    warningsAsErrors = true
}