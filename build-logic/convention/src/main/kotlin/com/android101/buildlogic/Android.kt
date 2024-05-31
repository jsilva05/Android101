package com.android101.buildlogic

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal object AndroidVersions {
    const val CompileSdk = 34
    const val MinSdk = 28
    const val TargetSdk = 34
}

internal fun Project.configureAndroid(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {

        compileSdk = AndroidVersions.CompileSdk

        defaultConfig {
            minSdk = AndroidVersions.MinSdk
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17

            // https://developer.android.com/studio/write/java8-support
            isCoreLibraryDesugaringEnabled = true
        }
    }

    dependencies {
        "coreLibraryDesugaring"(libs.findLibrary("coreLibraryDesugaring").get())
    }
}
