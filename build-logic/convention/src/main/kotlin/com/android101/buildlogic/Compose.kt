package com.android101.buildlogic

import com.android.build.api.dsl.CommonExtension
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeCompilerGradlePluginExtension

internal fun Project.configureCompose (
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    with(pluginManager) {
        apply("org.jetbrains.kotlin.plugin.compose")
        apply("org.jetbrains.kotlin.plugin.serialization")
    }

    commonExtension.apply {
        buildFeatures {
            compose = true
        }
    }

    composeCompiler {
        enableStrongSkippingMode.set(true)
    }

    dependencies {
        val bom = libs.findLibrary("androidx.compose.bom").get()
        "implementation"(platform(bom))

        "implementation"(libs.findLibrary("androidx.compose.ui").get())
        "implementation"(libs.findLibrary("androidx.compose.foundation").get())
        "implementation"(libs.findLibrary("androidx.compose.material3").get())
        "implementation"(libs.findLibrary("androidx.compose.viewmodel").get())
        "implementation"(libs.findLibrary("androidx.hilt.navigation.compose").get())

        "implementation"(libs.findLibrary("androidx.compose.ui.tooling").get())
        "debugImplementation"(libs.findLibrary("androidx.compose.ui.tooling.preview").get())

        // Serialization required for navigation purposes
        "implementation"(libs.findLibrary("kotlin.serialization").get())
    }
}

private fun Project.composeCompiler(action: ComposeCompilerGradlePluginExtension.() -> Unit) =
    extensions.configure<ComposeCompilerGradlePluginExtension>(action)