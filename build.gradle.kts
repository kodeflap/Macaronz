buildscript {
    repositories {
        google()
        mavenCentral()
        maven { url "https://plugins.gradle.org/m2/" }
    }
    dependencies {
        classpath Dependencies.androidGradlePlugin
        classpath Dependencies.kotlinGradlePlugin
        classpath Dependencies.spotlessGradlePlugin
        classpath Dependencies.gradleNexusPublishPlugin
        classpath Dependencies.dokka
        classpath Dependencies.kotlinBinaryValidator
    }
}

task clean(type: Delete) {
    delete rootProject.buildDir
}

