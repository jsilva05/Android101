plugins {
    alias(libs.plugins.android101.android.library)
    alias(libs.plugins.sql.delight)
}

android {
    namespace = "com.android101.persistence.database"
}

android101 {
    hilt()
}

sqldelight {
    databases {
        create("Android101Database") {
            packageName.set("com.android101.persistence.database")
            schemaOutputDirectory.set(file("src/main/sqldelight/databases"))
            generateAsync = true
            verifyMigrations = true

            dependency(project(":feature:list:db"))
        }
    }
}

dependencies {
    implementation(libs.sql.delight.driver)

    implementation(projects.feature.list.db)
}
