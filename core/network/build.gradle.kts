plugins {
	alias(libs.plugins.library)
}

java {
	sourceCompatibility = JavaVersion.VERSION_17
	targetCompatibility = JavaVersion.VERSION_17
}

android {
	namespace = "ru.nikpanfilov.delivery.core.network"
}

dependencies {
	implementation(project(":core:token"))

	api(libs.moshi)
	api(libs.moshi.adapter)
	api(libs.retrofit)
	api(libs.retrofit.scalars)
	api(libs.retrofit.moshi)
	implementation(libs.okhttp.interceptor)
	kapt(libs.moshi.codegen)
}