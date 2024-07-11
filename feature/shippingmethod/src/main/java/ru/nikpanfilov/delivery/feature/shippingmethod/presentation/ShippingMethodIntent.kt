package ru.nikpanfilov.delivery.feature.shippingmethod.presentation

import ru.nikpanfilov.delivery.core.presentation.Intent
import ru.nikpanfilov.delivery.shared.deliveryinfo.domain.entity.DeliveryOption

sealed interface ShippingMethodIntent : Intent {

	data object NavigateBack : ShippingMethodIntent

	data class ChooseDeliveryOption(
		val option: DeliveryOption,
	) : ShippingMethodIntent
}