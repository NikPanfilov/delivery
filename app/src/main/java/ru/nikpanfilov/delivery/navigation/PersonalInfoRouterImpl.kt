package ru.nikpanfilov.delivery.navigation

import ru.nikpanfilov.delivery.core.navigation.GlobalRouter
import ru.nikpanfilov.delivery.feature.addressinfo.AddressInfoDestination
import ru.nikpanfilov.delivery.feature.personalinfo.presentation.PersonalInfoRouter
import javax.inject.Inject

class PersonalInfoRouterImpl @Inject constructor(
	private val router: GlobalRouter,
) : PersonalInfoRouter {

	override fun navigateBack() {
		router.navigateBack()
	}

	override fun navigateToAddressInfo() {
		router.navigateTo(AddressInfoDestination)
	}
}