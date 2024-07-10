plugins {
	alias(libs.plugins.library)
	alias(libs.plugins.android.library)
	alias(libs.plugins.compose.library)
	alias(libs.plugins.kotlin.serialization)
}

android {
	namespace = "ru.nikpanfilov.delivery.feature.profile"
}

dependencies {
	implementation(project(":core:presentation"))
	implementation(project(":core:ui"))
	implementation(project(":core:network"))
	implementation(project(":core:navigation"))
	implementation(project(":core:error"))

	implementation(project(":shared:validators"))
	implementation(project(":shared:user"))
	implementation(project(":shared:cities"))

	implementation(libs.compose.ui)
	implementation(libs.compose.material)
	implementation(libs.compose.preview)
	implementation(libs.compose.tooling)
	implementation(libs.android.core)
}