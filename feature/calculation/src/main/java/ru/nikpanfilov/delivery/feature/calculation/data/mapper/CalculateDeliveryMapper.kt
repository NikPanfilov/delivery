package ru.nikpanfilov.delivery.feature.calculation.data.mapper

import ru.nikpanfilov.delivery.feature.calculation.data.dto.CalculateDeliveryDto
import ru.nikpanfilov.delivery.feature.calculation.domain.entity.CalculateDelivery
import ru.nikpanfilov.delivery.shared.cities.data.mapper.toDto

internal fun CalculateDelivery.toDto(): CalculateDeliveryDto = CalculateDeliveryDto(
	packageType = packageType.toDto(),
	senderPoint = senderPoint.toDto(),
	receiverPoint = receiverPoint.toDto(),
)