plugins {
	alias(libs.plugins.library)
	alias(libs.plugins.kotlin.serialization)
}

android {
	namespace = "ru.nikpanfilov.delivery.shared.deliveryinfo"
}

dependencies {
	implementation(project(":core:network"))
	implementation(libs.kotlinx.serialization)
}