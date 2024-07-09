package ru.nikpanfilov.delivery.feature.signin.data.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SignInResponseDto(
	val token: String,
)
