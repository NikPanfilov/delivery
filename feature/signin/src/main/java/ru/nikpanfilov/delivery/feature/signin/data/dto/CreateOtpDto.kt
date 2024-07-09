package ru.nikpanfilov.delivery.feature.signin.data.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreateOtpDto(
	val phone: String,
)
