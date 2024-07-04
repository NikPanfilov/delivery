import com.android.build.gradle.BaseExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import ru.nikpanfilov.delivery.configureCompose

class ComposeConventionPlugin : Plugin<Project> {

	override fun apply(target: Project) {
		with(target) {
			val extension = extensions.getByType<BaseExtension>()
			configureCompose(extension)
		}
	}
}