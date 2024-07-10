package ru.nikpanfilov.delivery.shared.user.data.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserDto(
	val phone: String,
	val firstname: String?,
	val middlename: String?,
	val lastname: String?,
	val email: String?,
	val city: String?,
)
