plugins {
    alias(libs.plugins.kotlinMultiplatform)
}

kotlin {
    iosX64()
    iosArm64()
    iosSimulatorArm64()

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
        val iosMain by creating {
            dependencies {
                implementation(libs.ktor.client.ios)
                implementation(project(":app:shared"))
            }
        }
    }
}
