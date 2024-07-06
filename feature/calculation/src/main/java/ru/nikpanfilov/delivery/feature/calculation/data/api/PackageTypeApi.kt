package ru.nikpanfilov.delivery.feature.calculation.data.api

import retrofit2.http.GET
import ru.nikpanfilov.delivery.feature.calculation.data.dto.DeliveryPackageTypeResponseDto

interface PackageTypeApi {

	@GET("/delivery/package/types")
	suspend fun getTypes(): DeliveryPackageTypeResponseDto
}