plugins {
	alias(libs.plugins.application)
	alias(libs.plugins.compose.library)
	alias(libs.plugins.kotlin.serialization)
}

android {
	namespace = "ru.nikpanfilov.delivery"

	defaultConfig {
		applicationId = "ru.nikpanfilov.delivery"
	}
}

dependencies {
	implementation(libs.appcompat)
	implementation(libs.android.core)
	implementation(libs.compose.activity)
	implementation(libs.compose.navigation)
	implementation(libs.compose.material)

	implementation(project(":core:navigation"))
	implementation(project(":core:token"))
	implementation(project(":core:network"))
	implementation(project(":core:ui"))
	implementation(project(":core:presentation"))
	implementation(project(":core:error"))
}
