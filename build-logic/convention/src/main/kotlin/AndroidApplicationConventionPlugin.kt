import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.plugin.KaptExtension
import ru.nikpanfilov.delivery.configureAndroid

class AndroidApplicationConventionPlugin : Plugin<Project> {

	override fun apply(target: Project) {
		with(target) {
			with(pluginManager) {
				apply("com.android.application")
				apply("org.jetbrains.kotlin.android")
				apply("org.jetbrains.kotlin.kapt")
			}

			extensions.configure<BaseAppModuleExtension> {
				configureAndroid(commonExtension = this)
			}

			val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
			dependencies {
				add("implementation", libs.findLibrary("dagger").get())
				add("kapt", libs.findLibrary("dagger.compiler").get())
				add("kapt", libs.findLibrary("kotlin.stdlib").get())
				add("kapt", libs.findLibrary("compose.runtime").get())
			}

			val kaptExtension = extensions.getByType<KaptExtension>()
			kaptExtension.apply {
				correctErrorTypes = true
			}
		}
	}
}
