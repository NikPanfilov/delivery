package ru.nikpanfilov.delivery.feature.profile.data.api

import retrofit2.http.Body
import retrofit2.http.PATCH
import ru.nikpanfilov.delivery.feature.profile.data.dto.UserUpdateDto

interface ProfileApi {

	@PATCH("/users/profile")
	suspend fun update(@Body userUpdateDto: UserUpdateDto)
}