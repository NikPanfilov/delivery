package ru.nikpanfilov.delivery.di

import dagger.Binds
import dagger.Module
import ru.nikpanfilov.delivery.core.navigation.GlobalRouter
import ru.nikpanfilov.delivery.core.navigation.NavControllerHolder
import ru.nikpanfilov.delivery.feature.calculation.presentation.CalculationRouter
import ru.nikpanfilov.delivery.feature.personalinfo.presentation.PersonalInfoRouter
import ru.nikpanfilov.delivery.feature.profile.presentation.ProfileRouter
import ru.nikpanfilov.delivery.feature.shippingmethod.presentation.ShippingMethodRouter
import ru.nikpanfilov.delivery.feature.signin.presentation.SignInRouter
import ru.nikpanfilov.delivery.navigation.CalculationRouterImpl
import ru.nikpanfilov.delivery.navigation.GlobalRouterImpl
import ru.nikpanfilov.delivery.navigation.MainRouterImpl
import ru.nikpanfilov.delivery.navigation.PersonalInfoRouterImpl
import ru.nikpanfilov.delivery.navigation.ProfileRouterImpl
import ru.nikpanfilov.delivery.navigation.ShippingMethodRouterImpl
import ru.nikpanfilov.delivery.navigation.SignInRouterImpl
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

	@Binds
	fun bindSignInRouter(signInRouterImpl: SignInRouterImpl): SignInRouter

	@Binds
	fun bindProfileRouter(profileRouterImpl: ProfileRouterImpl): ProfileRouter

	@Binds
	fun bindShippingMethodRouter(shippingMethodRouterImpl: ShippingMethodRouterImpl): ShippingMethodRouter

	@Binds
	fun bindPersonalInfoRouter(personalInfoRouterImpl: PersonalInfoRouterImpl): PersonalInfoRouter
}
