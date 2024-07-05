plugins {
	alias(libs.plugins.library)
}

java {
	sourceCompatibility = JavaVersion.VERSION_17
	targetCompatibility = JavaVersion.VERSION_17
}
android {
	namespace = "ru.nikpanfilov.delivery.core.navigation"
}

dependencies {
	implementation(libs.compose.navigation)
}
