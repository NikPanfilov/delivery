package ru.nikpanfilov.delivery.shared.user.data.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SessionResponseDto(
	val user: UserDto,
)
