package ru.nikpanfilov.delivery.shared.deliveryinfo.data.mapper

import ru.nikpanfilov.delivery.shared.deliveryinfo.data.dto.DeliveryOptionDto
import ru.nikpanfilov.delivery.shared.deliveryinfo.domain.entity.DeliveryOption
import ru.nikpanfilov.delivery.shared.deliveryinfo.domain.entity.DeliveryType

fun List<DeliveryOptionDto>.toEntity(): List<DeliveryOption> =
	this.map(DeliveryOptionDto::toEntity)

fun DeliveryOptionDto.toEntity(): DeliveryOption = DeliveryOption(
	id = id,
	price = price,
	days = days,
	name = name,
	type = DeliveryType.entries.find { it.name == type } ?: error("DeliverType does not exists"),
)

fun DeliveryOption.toDto(): DeliveryOptionDto = DeliveryOptionDto(
	id = id,
	price = price,
	days = days,
	name = name,
	type = type.name,
)