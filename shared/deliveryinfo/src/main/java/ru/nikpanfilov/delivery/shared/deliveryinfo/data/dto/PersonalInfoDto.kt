package ru.nikpanfilov.delivery.shared.deliveryinfo.data.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PersonalInfoDto(
	val firstname: String,
	val lastname: String,
	val middlename: String,
	val phone: String,
)
