package ru.nikpanfilov.delivery.shared.deliveryinfo.data.mapper

import ru.nikpanfilov.delivery.shared.deliveryinfo.data.dto.PersonalInfoDto
import ru.nikpanfilov.delivery.shared.deliveryinfo.domain.entity.PersonalInfo

fun PersonalInfo.toDto(): PersonalInfoDto = PersonalInfoDto(
	firstname = firstname,
	lastname = lastname,
	middlename = middlename,
	phone = phone,
)

fun PersonalInfoDto.toEntity(): PersonalInfo = PersonalInfo(
	firstname = firstname,
	lastname = lastname,
	middlename = middlename,
	phone = phone,
)