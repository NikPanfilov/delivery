package ru.nikpanfilov.delivery.feature.calculation.data.api

import retrofit2.http.Body
import retrofit2.http.GET
import ru.nikpanfilov.delivery.feature.calculation.data.dto.CalculateDeliveryDto
import ru.nikpanfilov.delivery.feature.calculation.data.dto.CalculateDeliveryResponse

interface CalculationApi {

	@GET("/delivery/calc")
	suspend fun calculate(@Body calculateDeliveryDto: CalculateDeliveryDto): CalculateDeliveryResponse
}