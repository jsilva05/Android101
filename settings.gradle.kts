@file:Suppress("UnstableApiUsage")
pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex(".*google.*")
                includeGroupByRegex(".*android.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Android101"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(":app")

include(":backend:core")

include(":core:moshi")

include(":feature:list:impl")
include(":feature:list:api")
include(":feature:list:ui")

include(":navigation")

include(":ui:core")
include(":ui:theme")
include(":ui:shared")
