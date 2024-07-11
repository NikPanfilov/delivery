package ru.nikpanfilov.delivery.feature.shippingmethod.presentation

import ru.nikpanfilov.delivery.core.presentation.State
import ru.nikpanfilov.delivery.shared.deliveryinfo.domain.entity.DeliveryOption

data class ShippingMethodState(
	val options: List<DeliveryOption>,
) : State
