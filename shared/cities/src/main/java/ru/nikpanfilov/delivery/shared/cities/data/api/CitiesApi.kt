package ru.nikpanfilov.delivery.shared.cities.data.api

import retrofit2.http.GET
import ru.nikpanfilov.delivery.shared.cities.data.dto.DeliveryPointsResponseDto

interface CitiesApi {

	@GET("/delivery/points")
	suspend fun getCities(): DeliveryPointsResponseDto
}