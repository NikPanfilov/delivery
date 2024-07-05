plugins {
	alias(libs.plugins.library)
	alias(libs.plugins.compose.library)
}

android {
	namespace = "ru.nikpanfilov.delivery.shared.validators"
}

dependencies {
	implementation(libs.compose.runtime)
	implementation(libs.compose.ui)
}