package ru.nikpanfilov.delivery.shared.deliveryinfo.data.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DeliveryOptionDto(
	val id: String,
	val price: Double,
	val days: Double,
	val name: String,
	val type: String,
)
