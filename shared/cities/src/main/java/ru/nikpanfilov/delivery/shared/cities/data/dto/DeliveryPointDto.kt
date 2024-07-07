package ru.nikpanfilov.delivery.shared.cities.data.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DeliveryPointDto(
	val id: String,
	val name: String,
	val latitude: Double,
	val longitude: Double,
)
