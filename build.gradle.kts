// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    extra["compose_version"] = "1.1.0-alpha03"
    extra["kotlin_version"] = "1.5.30"
    extra["ksp_version"] = "1.5.30-1.0.0"
    extra["lyricist_version"] = "1.0.0"

    val kotlin_version: String by rootProject.extra
    val ksp_version: String by rootProject.extra

    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.1.0-alpha11")
        classpath(kotlin("gradle-plugin", version = kotlin_version))
        // KSP
        classpath("com.google.devtools.ksp:com.google.devtools.ksp.gradle.plugin:$ksp_version")
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}