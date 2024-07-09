package ru.nikpanfilov.delivery.shared.user.data.api

import retrofit2.http.GET
import ru.nikpanfilov.delivery.shared.user.data.dto.SessionResponseDto

interface UserApi {

	@GET("/users/session")
	suspend fun get(): SessionResponseDto
}