include(":core:network")


pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)

    @Suppress("UnstableApiUsage")
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "SmartWeatherForecastApp"
include(":app")
include(":core:common")
include(":core:network")
include(":core:feature_api")
include(":feature:weather")
include(":feature:weather:data")
include(":feature:weather:domain")
include(":feature:cities_list")
include(":feature:weather:ui")
include(":assets")
