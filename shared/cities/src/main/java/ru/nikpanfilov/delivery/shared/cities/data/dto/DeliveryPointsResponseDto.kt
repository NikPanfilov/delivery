package ru.nikpanfilov.delivery.shared.cities.data.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DeliveryPointsResponseDto(
	val points: List<DeliveryPointDto>,
)
