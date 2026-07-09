plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

android {
    namespace = "com.edwinpaye.signaturewallet"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.edwinpaye.signaturewallet"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0.0"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

kotlin {
    androidTarget()

    sourceSets {
        val androidMain by getting {
            dependencies {
                implementation(libs.androidx.appcompat)
                implementation(libs.androidx.activityCompose)
                implementation(libs.androidx.coreKtx)
                implementation(libs.androidx.workManager)
                implementation(libs.androidx.biometric)
                implementation(libs.androidx.security.crypto)
                implementation(libs.androidx.cameraCore)
                implementation(libs.androidx.cameraLifecycle)
                implementation(libs.androidx.cameraView)
                implementation(libs.ktor.client.android)
                implementation(libs.room.androidDriver)
                implementation(libs.koin.android)

                implementation(project(":app:shared"))
            }
        }
    }
}

dependencies {
    implementation(libs.androidx.activityCompose)
}
