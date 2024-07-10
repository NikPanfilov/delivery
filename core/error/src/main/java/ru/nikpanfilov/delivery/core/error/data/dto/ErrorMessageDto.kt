package ru.nikpanfilov.delivery.core.error.data.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ErrorMessageDto(
	val statusCode: Int,
	val message: String,
)
