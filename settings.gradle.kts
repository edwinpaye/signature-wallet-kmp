pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }

    versionCatalogs {
        create("libs") {
            from(files("gradle/libs.versions.toml"))
        }
    }
}

rootProject.name = "signature-wallet-kmp"
include(":app:android")
include(":app:desktop")
include(":app:shared")
include(":core:common")
include(":core:design-system")
include(":core:security")
include(":feature:auth")
include(":feature:wallet")
include(":feature:signature")
include(":feature:transactions")
