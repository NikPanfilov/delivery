package ru.nikpanfilov.delivery.shared.cities.data.mapper

import ru.nikpanfilov.delivery.shared.cities.data.dto.DeliveryPointDto
import ru.nikpanfilov.delivery.shared.cities.data.dto.DeliveryPointsResponseDto
import ru.nikpanfilov.delivery.shared.cities.domain.entity.DeliveryPoint

fun DeliveryPointsResponseDto.toDeliveryPoints(): List<DeliveryPoint> =
	this.points.map(DeliveryPointDto::toEntity)

fun DeliveryPointDto.toEntity(): DeliveryPoint = DeliveryPoint(
	id = id,
	name = name,
	latitude = latitude,
	longitude = longitude,
)

fun DeliveryPoint.toDto(): DeliveryPointDto = DeliveryPointDto(
	id = id,
	name = name,
	latitude = latitude,
	longitude = longitude,
)