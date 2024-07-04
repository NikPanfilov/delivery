package ru.nikpanfilov.delivery.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.nikpanfilov.delivery.App
import ru.nikpanfilov.delivery.MainActivity
import ru.nikpanfilov.delivery.core.navigation.GlobalRouter
import ru.nikpanfilov.delivery.core.navigation.NavControllerHolder
import ru.nikpanfilov.delivery.core.network.di.NetworkModule
import ru.nikpanfilov.delivery.core.token.di.TokenModule
import ru.nikpanfilov.delivery.feature.signin.di.SignInModule
import ru.nikpanfilov.delivery.feature.signin.presentation.SignInViewModel
import javax.inject.Singleton

@Component(
	modules = []
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
}
