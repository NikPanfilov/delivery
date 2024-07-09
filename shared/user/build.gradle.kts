plugins {
	alias(libs.plugins.library)
}

android {
	namespace = "ru.nikpanfilov.delivery.shared.user"
}

dependencies {
	implementation(project(":core:network"))
}