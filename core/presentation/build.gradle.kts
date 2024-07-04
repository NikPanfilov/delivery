plugins {
	alias(libs.plugins.library)
	alias(libs.plugins.android.library)
}

android {
	namespace = "ru.nikpanfilov.delivery.core.presentation"
}

dependencies {
	implementation(libs.coroutine.android)
	implementation(libs.compose.activity)
	api(libs.lifecycle.viewmodel)
	api(libs.lifecycle.viewmodel.savedstate)
	api(libs.kotlinx.serialization)
}