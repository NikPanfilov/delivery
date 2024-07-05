package ru.nikpanfilov.delivery

import android.app.Application
import ru.nikpanfilov.delivery.di.AppComponent
import ru.nikpanfilov.delivery.di.DaggerAppComponent

class App : Application() {

	val appComponent: AppComponent by lazy {
		DaggerAppComponent
			.builder()
			.context(this)
			.build()
	}
}