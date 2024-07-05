package ru.nikpanfilov.delivery.navigation

import androidx.navigation.NavController
import ru.nikpanfilov.delivery.core.navigation.Destination
import ru.nikpanfilov.delivery.core.navigation.GlobalRouter
import ru.nikpanfilov.delivery.core.navigation.NavControllerHolder
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GlobalRouterImpl @Inject constructor() : GlobalRouter, NavControllerHolder {

	private var navController: NavController? = null

	override fun setNavController(navController: NavController) {
		this.navController = navController
	}

	override fun removeNavController() {
		navController = null
	}

	override fun navigateTo(destination: Destination) {
		navController?.navigate(destination)
	}

	override fun newRootScreen(destination: Destination) {
		val navController = navController ?: return
		navController.navigate(destination) {
			popUpTo(navController.graph.startDestinationId)
			launchSingleTop = true
		}
	}

	override fun navigateBack() {
		navController?.popBackStack()
	}
}