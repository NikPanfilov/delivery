package ru.nikpanfilov.delivery.navigation

import ru.nikpanfilov.delivery.core.navigation.GlobalRouter
import ru.nikpanfilov.delivery.feature.calculation.presentation.CalculationDestination
import ru.nikpanfilov.delivery.feature.signin.presentation.SignInRouter
import javax.inject.Inject

class SignInRouterImpl @Inject constructor(
	private val router: GlobalRouter,
) : SignInRouter {

	override fun navigateBack() {
		router.navigateBack()
	}

	override fun setNewRoot() {
		router.newRootScreen(CalculationDestination)
	}
}