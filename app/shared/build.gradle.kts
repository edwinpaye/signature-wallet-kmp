plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.room)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }

    jvm("desktop")

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "SignatureWallet"
            isStatic = true
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.materialIconsExtended)
                implementation(compose.ui)
                implementation(compose.components.resources)
                implementation(compose.components.uiToolingPreview)

                // Kotlin
                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.kotlinx.serialization.json)
                implementation(libs.kotlinx.datetime)

                // Ktor Client
                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.logging)
                implementation(libs.ktor.client.content.negotiation)
                implementation(libs.ktor.serialization.json)

                // Database
                implementation(libs.room.runtime)
                implementation(libs.sqlite.bundled)

                // DI
                implementation(libs.koin.core)
                implementation(libs.koin.compose)

                // Navigation
                implementation(libs.voyager.navigator)
                implementation(libs.voyager.transitions)

                // Logging
                implementation(libs.napier)

                // Core modules
                implementation(project(":core:common"))
                implementation(project(":core:design-system"))
                implementation(project(":core:security"))

                // Feature modules
                implementation(project(":feature:auth"))
                implementation(project(":feature:wallet"))
                implementation(project(":feature:signature"))
                implementation(project(":feature:transactions"))
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(libs.kotest.framework.engine)
                implementation(libs.kotest.assertions.core)
                implementation(libs.mockk)
                implementation(libs.turbine)
                implementation(libs.kotlinx.coroutines.test)
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(libs.androidx.appcompat)
                implementation(libs.androidx.activityCompose)
                implementation(libs.androidx.coreKtx)
                implementation(libs.androidx.workManager)
                implementation(libs.androidx.biometric)
                implementation(libs.androidx.security.crypto)

                // CameraX
                implementation(libs.androidx.cameraCore)
                implementation(libs.androidx.cameraLifecycle)
                implementation(libs.androidx.cameraView)

                // Ktor
                implementation(libs.ktor.client.android)

                // Room
                implementation(libs.room.androidDriver)

                // Koin Android
                implementation(libs.koin.android)
            }
        }

        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation(libs.ktor.client.java)
                implementation(libs.room.jvm.driver)
            }
        }

        val iosMain by getting {
            dependencies {
                implementation(libs.ktor.client.ios)
            }
        }
    }
}

android {
    namespace = "com.edwinpaye.signaturewallet"
    compileSdk = 35

    defaultConfig {
        minSdk = 24
        targetSdk = 35
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }
}

room {
    schemaDirectory("$projectDir/schemas")
}
