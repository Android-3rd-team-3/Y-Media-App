// Top-level build file where you can add configuration options common to all sub-projects/modules.
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    id("com.google.devtools.ksp") version "1.8.21-1.0.11" apply false
    alias(libs.plugins.safeArgs) apply false
}



//buildscript {
//    repositories {
//        google()
//    }
//    dependencies {
//        val nav_version = "2.7.7"
//        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version")
//    }
//}