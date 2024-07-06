package ru.nikpanfilov.delivery.navigation

import ru.nikpanfilov.delivery.core.navigation.GlobalRouter
import ru.nikpanfilov.delivery.presentation.MainRouter
import javax.inject.Inject

class MainRouterImpl @Inject constructor(
	private val router: GlobalRouter,
) : MainRouter {

	override fun navigateToCalculation() {
		//Добавить экран расчётов
	}

	override fun navigateToHistory() {
		//Добавить экран истории
	}

	override fun navigateToProfile() {
		//Добавить экран профиля
	}

	override fun navigateToSignIn() {
		//Добавить экран входа
	}
}