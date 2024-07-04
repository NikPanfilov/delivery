package ru.nikpanfilov.delivery.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.nikpanfilov.delivery.MainActivity
import ru.nikpanfilov.delivery.core.navigation.GlobalRouter
import ru.nikpanfilov.delivery.core.navigation.NavControllerHolder
import ru.nikpanfilov.delivery.core.network.di.NetworkModule
import ru.nikpanfilov.delivery.core.token.di.TokenModule
import javax.inject.Singleton

@Component(
	modules = [
		RoutersModule::class,
		TokenModule::class,
		NetworkModule::class,
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
