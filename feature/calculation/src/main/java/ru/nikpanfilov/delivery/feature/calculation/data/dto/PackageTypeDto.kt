package ru.nikpanfilov.delivery.feature.calculation.data.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PackageTypeDto(
	val id: String,
	val name: String,
	val length: Double,
	val width: Double,
	val height: Double,
	val weight: Double,
)
