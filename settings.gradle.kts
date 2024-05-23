pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        gradlePluginPortal()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url=uri("https://www.jitpack.io") }
    }
}

rootProject.name = "MemoCar"
include(":app")
include(":core")
include(":core:data")
include(":core:database")
include(":core:domain")
include(":core:model")
include(":core:common")
include(":feature")
include(":feature:dashboard")
include(":feature:car")
include(":feature:brand")
include(":core:ui")
include(":feature:category")
include(":feature:detail")
include(":core:designsystem")
include(":feature:setting")
