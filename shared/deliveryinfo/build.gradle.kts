plugins {
	alias(libs.plugins.library)
}

android {
	namespace = "ru.nikpanfilov.delivery.shared.deliveryinfo"
}

dependencies {
	implementation(project(":core:network"))
}