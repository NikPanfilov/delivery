package ru.nikpanfilov.delivery.feature.profile.data.mapper

import ru.nikpanfilov.delivery.feature.profile.data.dto.UserUpdateDto
import ru.nikpanfilov.delivery.shared.user.data.mapper.toDto
import ru.nikpanfilov.delivery.shared.user.domain.entity.User

internal fun User.toUpdateDto(): UserUpdateDto = UserUpdateDto(
	phone = phone,
	profile = this.toDto(),
)