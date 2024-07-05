buildscript {
	repositories {
		google()
		mavenCentral()
	}
	dependencies {
		classpath(libs.gradle)
		classpath(libs.kotlin)
		classpath(libs.dagger)
	}
}

plugins {
	alias(libs.plugins.kotlin.jvm) apply false
	alias(libs.plugins.application) apply false
	alias(libs.plugins.library) apply false
	alias(libs.plugins.compose.library) apply false
	alias(libs.plugins.android.library) apply false
	alias(libs.plugins.kotlin.android) apply false
}
