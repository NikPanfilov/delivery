package ru.nikpanfilov.delivery.navigation

import ru.nikpanfilov.delivery.core.navigation.GlobalRouter
import ru.nikpanfilov.delivery.feature.addressinfo.presentation.AddressInfoRouter
import javax.inject.Inject

class AddressInfoRouterImpl @Inject constructor(
	private val router: GlobalRouter,
) : AddressInfoRouter {

	override fun navigateBack() {
		router.navigateBack()
	}

	override fun navigateToShipPayment() {
		//TODO(Добавить экран)
	}
}