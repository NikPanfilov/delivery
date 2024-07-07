package ru.nikpanfilov.delivery.di

import dagger.Binds
import dagger.Module
import ru.nikpanfilov.delivery.core.navigation.GlobalRouter
import ru.nikpanfilov.delivery.core.navigation.NavControllerHolder
import ru.nikpanfilov.delivery.feature.calculation.presentation.CalculationRouter
import ru.nikpanfilov.delivery.navigation.CalculationRouterImpl
import ru.nikpanfilov.delivery.navigation.GlobalRouterImpl
import ru.nikpanfilov.delivery.navigation.MainRouterImpl
import ru.nikpanfilov.delivery.presentation.MainRouter
import javax.inject.Singleton

@Module
interface RoutersModule {

	@Binds
	@Singleton
	fun bindGlobalRouter(globalRouterImpl: GlobalRouterImpl): GlobalRouter

	@Binds
	@Singleton
	fun bindNavControllerHolder(globalRouterImpl: GlobalRouterImpl): NavControllerHolder

	@Binds
	fun bindMainRouter(mainRouterImpl: MainRouterImpl): MainRouter

	@Binds
	fun bindCalculationRouter(calculationRouterImpl: CalculationRouterImpl): CalculationRouter
}
