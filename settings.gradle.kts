@file:Suppress("UnstableApiUsage")

include(":sliderzsample")


pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven(url = "https://plugins.gradle.org/m2/")
    }
}
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://plugins.gradle.org/m2/")
    }
}

rootProject.name = "Sliderz"
include(":app")
include(":sliderz")
include(":benchmark")

