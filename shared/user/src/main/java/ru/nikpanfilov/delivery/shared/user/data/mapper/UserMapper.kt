package ru.nikpanfilov.delivery.shared.user.data.mapper

import ru.nikpanfilov.delivery.shared.user.data.dto.UserDto
import ru.nikpanfilov.delivery.shared.user.domain.entity.User

fun UserDto.toEntity(): User = User(
	phone = phone,
	firstname = firstname ?: "",
	middlename = middlename ?: "",
	lastname = lastname ?: "",
	email = email ?: "",
	city = city ?: "",
)

fun User.toDto(): UserDto = UserDto(
	phone = phone,
	firstname = firstname,
	middlename = middlename,
	lastname = lastname,
	email = email,
	city = city,
)