package ru.nikpanfilov.delivery.feature.calculation.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ru.nikpanfilov.delivery.shared.cities.data.dto.DeliveryPointDto

@JsonClass(generateAdapter = true)
data class CalculateDeliveryDto(
	@Json(name = "package") val packageType: PackageTypeDto,
	val senderPoint: DeliveryPointDto,
	val receiverPoint: DeliveryPointDto,
)
