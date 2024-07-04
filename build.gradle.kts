buildscript {
	repositories {
		google()
		mavenCentral()
	}
	dependencies {
		classpath(libs.gradle)
		classpath(libs.kotlin)
	}
}

plugins {
	alias(libs.plugins.kotlin.jvm) apply false
	alias(libs.plugins.application) apply false
	alias(libs.plugins.kotlin.android) apply false
}
