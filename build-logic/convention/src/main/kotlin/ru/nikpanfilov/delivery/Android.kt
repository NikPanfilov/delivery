package ru.nikpanfilov.delivery

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion

internal fun configureAndroid(commonExtension: CommonExtension<*, *, *, *, *, *>) {
	commonExtension.apply {
		compileSdk = 34

		defaultConfig {
			minSdk = 30
		}

		compileOptions {
			sourceCompatibility = JavaVersion.VERSION_17
			targetCompatibility = JavaVersion.VERSION_17
		}

		buildFeatures.buildConfig = false
	}
}
