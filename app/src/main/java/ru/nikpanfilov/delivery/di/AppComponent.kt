package ru.nikpanfilov.delivery.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.nikpanfilov.delivery.MainActivity
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
