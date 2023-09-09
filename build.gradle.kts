// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.org.jetbrains.kotlin.android) apply false
    alias(libs.plugins.com.google.dagger.hilt.android) apply false
}

tasks.register("clean"){
    delete(rootProject.buildDir)
}

tasks.register<Copy>("copy"){
    from("./app/build/outputs/apk/debug/app-debug.apk")
    into("./")
}

