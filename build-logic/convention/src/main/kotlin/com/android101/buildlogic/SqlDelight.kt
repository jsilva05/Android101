package com.android101.buildlogic

import app.cash.sqldelight.gradle.SqlDelightExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureSqlDelight(
    packageName: String,
) {
    with(pluginManager) {
        apply("app.cash.sqldelight")
    }

    sqlDelight {
        databases.create("Android101Database") {
            this.packageName.set(packageName)
            generateAsync.set(true)
        }
    }

    dependencies {
        "implementation"(libs.findLibrary("sql.delight.coroutines").get())
    }
}

private fun Project.sqlDelight(action: SqlDelightExtension.() -> Unit) =
    extensions.configure<SqlDelightExtension>(action)
