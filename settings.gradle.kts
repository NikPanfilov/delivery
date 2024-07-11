pluginManagement {
    includeBuild("build-logic")
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "delivery"

include(":app")
include(":core:navigation")
include(":core:token")
include(":core:network")
include(":core:ui")
include(":core:presentation")
include(":core:error")
include(":shared:validators")
include(":shared:cities")
include(":shared:deliveryinfo")
include(":shared:user")
include(":feature:calculation")
include(":feature:signin")
include(":feature:profile")
include(":feature:shippingmethod")
