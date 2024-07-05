package ru.nikpanfilov.delivery.di

import dagger.Binds
import dagger.Module
import ru.nikpanfilov.delivery.core.navigation.GlobalRouter
import ru.nikpanfilov.delivery.core.navigation.NavControllerHolder
import ru.nikpanfilov.delivery.navigation.GlobalRouterImpl
import javax.inject.Singleton

@Module
interface RoutersModule {

	@Binds
	@Singleton
	fun bindGlobalRouter(globalRouterImpl: GlobalRouterImpl): GlobalRouter

	@Binds
	@Singleton
	fun bindNavControllerHolder(globalRouterImpl: GlobalRouterImpl): NavControllerHolder
}
