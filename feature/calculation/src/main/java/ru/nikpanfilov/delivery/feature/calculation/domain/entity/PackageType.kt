package ru.nikpanfilov.delivery.feature.calculation.domain.entity

data class PackageType(
	val id: String,
	val name: String,
	val length: Double,
	val width: Double,
	val height: Double,
	val weight: Double,
)