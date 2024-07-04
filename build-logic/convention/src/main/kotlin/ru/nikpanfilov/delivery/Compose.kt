package ru.nikpanfilov.delivery

import com.android.build.gradle.BaseExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

internal fun Project.configureCompose(commonExtension: BaseExtension) {
	commonExtension.apply {
		val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

		buildFeatures.compose = true
		composeOptions.kotlinCompilerExtensionVersion = libs.findVersion("compose.compiler").get().toString()
	}
}