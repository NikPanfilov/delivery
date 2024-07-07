package ru.nikpanfilov.delivery.feature.calculation.domain.entity

import ru.nikpanfilov.delivery.shared.cities.domain.entity.DeliveryPoint

data class CalculateDelivery(
	val packageType: PackageType,
	val senderPoint: DeliveryPoint,
	val receiverPoint: DeliveryPoint,
)
