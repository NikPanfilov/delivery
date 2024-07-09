package ru.nikpanfilov.delivery.feature.profile.data.dto

import com.squareup.moshi.JsonClass
import ru.nikpanfilov.delivery.shared.user.data.dto.UserDto

@JsonClass(generateAdapter = true)
data class UserUpdateDto(
	val phone: String,
	val profile: UserDto,
)
