import com.google.devtools.ksp.processing.kspJsArgParserHelp

plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.kotlin.compose)
  alias(libs.plugins.kotlin.serialization)
  alias(libs.plugins.ksp)
  alias(libs.plugins.hilt)
}

android {
  namespace = "com.gshockv.dairyapp"
  compileSdk = 35

  defaultConfig {
    applicationId = "com.gshockv.dairyapp"
    minSdk = 31
    targetSdk = 35
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }
  kotlinOptions {
    jvmTarget = "11"
  }
  buildFeatures {
    compose = true
  }
}

dependencies {
  // core
  implementation(libs.bundles.androidX)

  // ui
  implementation(platform(libs.compose.bom))
  implementation(libs.bundles.compose)
  implementation(libs.splash.screen)

  implementation(libs.coil)

  // hilt
  implementation(libs.bundles.hilt)
  ksp(libs.hilt.compiler)

  // room
  implementation(libs.bundles.room)
  ksp(libs.room.compiler)

  implementation(libs.kotlinx.serialization.json)
  implementation(libs.gson)

  testImplementation(libs.junit)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)
  androidTestImplementation(platform(libs.compose.bom))
  androidTestImplementation(libs.compose.ui.test.junit4)

  debugImplementation(libs.compose.ui.tooling)
  debugImplementation(libs.compose.ui.test.manifest)
}
