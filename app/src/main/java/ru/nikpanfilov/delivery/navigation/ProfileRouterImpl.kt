package ru.nikpanfilov.delivery.navigation

import ru.nikpanfilov.delivery.core.navigation.GlobalRouter
import ru.nikpanfilov.delivery.feature.profile.presentation.ProfileRouter
import javax.inject.Inject

class ProfileRouterImpl @Inject constructor(
	private val router: GlobalRouter,
) : ProfileRouter {

	override fun navigateBack() {
		router.navigateBack()
	}
}