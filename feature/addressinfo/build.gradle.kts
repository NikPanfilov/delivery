plugins {
	alias(libs.plugins.library)
	alias(libs.plugins.android.library)
	alias(libs.plugins.compose.library)
	alias(libs.plugins.kotlin.serialization)
}

android {
	namespace = "ru.nikpanfilov.delivery.feature.deliveryinfo"
}

dependencies {
	implementation(project(":core:presentation"))
	implementation(project(":core:ui"))
	implementation(project(":core:navigation"))

	implementation(project(":shared:deliveryinfo"))
	implementation(project(":shared:validators"))

	implementation(libs.compose.ui)
	implementation(libs.compose.material)
	implementation(libs.compose.preview)
	implementation(libs.compose.tooling)
	implementation(libs.android.core)
}