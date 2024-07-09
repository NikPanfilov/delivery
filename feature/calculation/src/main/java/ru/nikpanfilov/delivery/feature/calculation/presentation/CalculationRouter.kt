package ru.nikpanfilov.delivery.feature.calculation.presentation

import ru.nikpanfilov.delivery.shared.deliveryinfo.domain.entity.DeliveryOption

interface CalculationRouter {

	fun navigateToDeliveryOptions(options: List<DeliveryOption>)
}