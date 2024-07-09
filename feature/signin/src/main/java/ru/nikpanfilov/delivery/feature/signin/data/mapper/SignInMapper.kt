package ru.nikpanfilov.delivery.feature.signin.data.mapper

import ru.nikpanfilov.delivery.feature.signin.data.dto.SignInDto
import ru.nikpanfilov.delivery.feature.signin.domain.entity.SignIn

internal fun SignIn.toDto(): SignInDto = SignInDto(
	phone = phone,
	code = code,
)