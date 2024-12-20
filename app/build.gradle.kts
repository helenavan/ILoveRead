plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose)
    alias(libs.plugins.dagger)
    kotlin("kapt")
    kotlin("plugin.serialization")
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.toulousehvl.iloveread"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.toulousehvl.iloveread"
        minSdk = 28
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        multiDexEnabled = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    testOptions {
        unitTests {
            isReturnDefaultValues = true
        }
    }
    kotlin {
        jvmToolchain(17)
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "/META-INF/gradle/incremental.annotation.processors"
        }
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    implementation(libs.androidx.multidex)

    //Navigation
    implementation(libs.navigation.compose)

    //Hilt
    implementation(libs.hilt.android)
    implementation(libs.hilt.android.compiler)
    implementation(libs.androidx.hilt.navigation.fragment)
    implementation(libs.androidx.hilt.navigation.compose)

    //Firebase
    implementation(libs.firebase.firestore)
    implementation(libs.firebase.auth)
    implementation(platform(libs.firebase.bom))

    kapt(libs.hilt.android.compiler)

    //Permissions
    implementation (libs.accompanist.permissions)

    //Retrofit
    implementation (libs.converter.gson)
    implementation (libs.retrofit)
    implementation (libs.retrofit2.kotlin.coroutines.adapter)
    //oKhttp
    implementation(libs.okhttp)
    //interceptor
    implementation(libs.logging.interceptor)

    //Serializable
    implementation (libs.jetbrains.kotlinx.serialization.json.v160)

    //Tests
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    testImplementation (libs.mockito.core)
    testImplementation (libs.kotlinx.coroutines.test)
}

// Allow references to generated code Rend le processus
// de compilation plus robuste, en particulier
// pour les projets complexes qui utilisent des bibliothèques
// d'annotations intensivement.
kapt {
    correctErrorTypes = true
}