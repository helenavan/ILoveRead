val snapshotVersion : String? = System.getenv("COMPOSE_SNAPSHOT_ID")
pluginManagement {
    repositories {
        google ()
        mavenCentral()
        gradlePluginPortal()
        mavenLocal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        snapshotVersion?.let {
            println("https://androidx.dev/snapshots/builds/$it/artifacts/repository/")
            maven { url = uri("https://androidx.dev/snapshots/builds/$it/artifacts/repository/") }
        }
        google()
        mavenCentral()
    }
}

rootProject.name = "ILoveRead"
include(":app")
 