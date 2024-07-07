package ru.nikpanfilov.delivery.feature.calculation.data.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DeliveryPackageTypeResponseDto(
	val packages: List<PackageTypeDto>,
)
