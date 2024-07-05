package ru.nikpanfilov.delivery.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.nikpanfilov.delivery.MainActivity
import ru.nikpanfilov.delivery.core.navigation.GlobalRouter
import ru.nikpanfilov.delivery.core.navigation.NavControllerHolder
import javax.inject.Singleton

@Component(
	modules = [
		RoutersModule::class,
	]
)
@Singleton
interface AppComponent {

	@Component.Builder
	interface Builder {

		@BindsInstance
		fun context(context: Context): Builder

		fun build(): AppComponent
	}

	fun inject(activity: MainActivity)

	val globalRouter: GlobalRouter

	val navControllerHolder: NavControllerHolder
}
