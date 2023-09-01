// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    val versions_list = {
        val compose = "1.0.1"
        val version_code = 1
        val version_name = "1.0.0"
    }
}

plugins {
    id("com.android.application") version "8.1.0" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
}

tasks.register("clean"){
    delete(rootProject.buildDir)
}

tasks.register<Copy>("copy"){
    from("./app/build/outputs/apk/debug/app-debug.apk")
    into("./")
}

