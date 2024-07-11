package ru.nikpanfilov.delivery.navigation

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import ru.nikpanfilov.delivery.core.navigation.GlobalRouter
import ru.nikpanfilov.delivery.feature.calculation.presentation.CalculationRouter
import ru.nikpanfilov.delivery.feature.shippingmethod.ShippingMethodDestination
import ru.nikpanfilov.delivery.shared.deliveryinfo.domain.entity.DeliveryOption
import javax.inject.Inject

class CalculationRouterImpl @Inject constructor(
	private val router: GlobalRouter,
) : CalculationRouter {

	override fun navigateToDeliveryOptions(options: List<DeliveryOption>) {
		router.navigateTo(ShippingMethodDestination(Json.encodeToString(options)))
	}
}