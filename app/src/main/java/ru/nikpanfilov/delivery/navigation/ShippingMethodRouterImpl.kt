package ru.nikpanfilov.delivery.navigation

import ru.nikpanfilov.delivery.core.navigation.GlobalRouter
import ru.nikpanfilov.delivery.feature.personalinfo.PersonalInfoDestination
import ru.nikpanfilov.delivery.feature.shippingmethod.presentation.ShippingMethodRouter
import javax.inject.Inject

class ShippingMethodRouterImpl @Inject constructor(
	private val router: GlobalRouter,
) : ShippingMethodRouter {

	override fun navigateBack() {
		router.navigateBack()
	}

	override fun navigateToSenderInfo() {
		router.navigateTo(PersonalInfoDestination)
	}
}