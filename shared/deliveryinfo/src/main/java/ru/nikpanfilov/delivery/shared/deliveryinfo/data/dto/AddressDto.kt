package ru.nikpanfilov.delivery.shared.deliveryinfo.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AddressDto(
	val street: String,
	val house: String,
	val apartment: String,
	@Json(name = "comment")
	val courierNote: String,
)
