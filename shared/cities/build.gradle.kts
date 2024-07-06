plugins {
	alias(libs.plugins.library)
}

android {
	namespace = "ru.nikpanfilov.delivery.shared.cities"
}

dependencies {
	implementation(project(":core:network"))
}