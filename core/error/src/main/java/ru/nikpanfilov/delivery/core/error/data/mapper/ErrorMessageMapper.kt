package ru.nikpanfilov.delivery.core.error.data.mapper

import ru.nikpanfilov.delivery.core.error.NetworkError
import ru.nikpanfilov.delivery.core.error.data.dto.ErrorMessageDto

internal fun ErrorMessageDto.toEntity(): NetworkError.ErrorMessage =
	NetworkError.ErrorMessage(
		statusCode = statusCode,
		message = message,
	)