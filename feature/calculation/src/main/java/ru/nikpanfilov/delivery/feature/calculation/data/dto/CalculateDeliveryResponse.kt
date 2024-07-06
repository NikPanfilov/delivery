package ru.nikpanfilov.delivery.feature.calculation.data.dto

import ru.nikpanfilov.delivery.shared.deliveryinfo.data.dto.DeliveryOptionDto

data class CalculateDeliveryResponse(
	val options: List<DeliveryOptionDto>,
)
