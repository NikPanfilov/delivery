@Suppress("DSL_SCOPE_VIOLATION") // Remove when fixed https://youtrack.jetbrains.com/issue/KTIJ-19369
plugins {
	alias(libs.plugins.library)
	alias(libs.plugins.compose.library)
}

android {
	namespace = "ru.nikpanfilov.delivery.core.ui"
}

dependencies {
	implementation(libs.compose.ui)
	implementation(libs.compose.material)
	implementation(libs.compose.preview)
	implementation(libs.compose.tooling)
	implementation(libs.android.core)
}
