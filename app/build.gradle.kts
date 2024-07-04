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

	implementation(project(":core:navigation"))
}
