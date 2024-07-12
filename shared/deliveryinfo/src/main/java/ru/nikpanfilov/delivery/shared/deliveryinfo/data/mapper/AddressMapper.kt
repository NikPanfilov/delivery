package ru.nikpanfilov.delivery.shared.deliveryinfo.data.mapper

import ru.nikpanfilov.delivery.shared.deliveryinfo.data.dto.AddressDto
import ru.nikpanfilov.delivery.shared.deliveryinfo.domain.entity.Address

fun AddressDto.toEntity(): Address = Address(
	street = street,
	house = house,
	apartment = apartment,
	courierNote = courierNote,
)

fun Address.toDto(): AddressDto = AddressDto(
	street = street,
	house = house,
	apartment = apartment,
	courierNote = courierNote,
)