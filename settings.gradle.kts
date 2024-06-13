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
include(":backend:spotify")

include(":core:impl")
include(":core:moshi")
include(":core:date-time")

include(":feature:authentication:impl")
include(":feature:authentication:api")
include(":feature:list:api")
include(":feature:list:db")
include(":feature:list:impl")
include(":feature:list:ui")

include(":navigation")

include(":persistence:database")

include(":ui:core")
include(":ui:shared")
include(":ui:theme")
