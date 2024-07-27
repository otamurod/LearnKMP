@file:Suppress("OPT_IN_USAGE")

import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.google.ksp)
  alias(libs.plugins.jetbrains.kotlin.multiplatform)
  alias(libs.plugins.jetbrains.kotlin.parcelize)
  alias(libs.plugins.jetbrains.kotlin.serialization)
  alias(libs.plugins.cash.sqldelight)
  alias(libs.plugins.kmp.nativeCoroutines)
}

version = "1.0"

sqldelight {
  databases {
    create("AppDb") {
      packageName.set("data")
    }
  }
}

android {
  compileSdk = libs.versions.android.sdk.compile.get().toInt()

  sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")

  defaultConfig {
    minSdk = libs.versions.android.sdk.min.get().toInt()
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }

  namespace = "com.kodeco.learn.shared"
}

kotlin {
  androidTarget()

  jvm("desktop")

  val xcf = XCFramework("SharedKit")

  listOf(
      iosX64(),
      iosArm64(),
      iosSimulatorArm64()
  ).forEach {
    it.binaries.framework {
      baseName = "SharedKit"
      xcf.add(this)

      export(project(":shared-dto"))
    }
  }

  sourceSets {
    targetHierarchy.default()

    getByName("commonMain") {
      dependencies {
        api(project(":shared-dto"))

        implementation(libs.kotlinx.datetime)
        implementation(libs.kotlinx.serialization.json)

        implementation(libs.ktor.client.core)
        implementation(libs.ktor.client.serialization)
        implementation(libs.ktor.client.content.negotiation)
        implementation(libs.ktor.client.logging)
        implementation(libs.ktor.serialization.kotlinx.json)

        implementation(libs.okio)
        implementation(libs.korio)
      }
    }

    getByName("commonTest") {
      dependencies {
        implementation(kotlin("test-common"))
        implementation(kotlin("test-annotations-common"))

        implementation(kotlin("test-junit"))
        implementation(libs.junit)
        implementation(libs.ktor.client.mock)
      }
    }

    getByName("androidMain") {
      dependencies {
        implementation(libs.cash.sqldelight.android)

        implementation(libs.ktor.client.android)
      }
    }

    getByName("androidUnitTest") {
      dependencies {
        implementation(kotlin("test-junit"))
      }
    }

    getByName("desktopMain") {
      dependencies {
        implementation(libs.cash.sqldelight.jvm)
      }
    }

    getByName("iosMain") {
      dependencies {
        implementation(libs.cash.sqldelight.native)

        implementation(libs.ktor.client.ios)
      }
    }
  }
}

kotlin.sourceSets.all {
  languageSettings.optIn("kotlin.RequiresOptIn")
  languageSettings.optIn("kotlin.experimental.ExperimentalObjCName")
}