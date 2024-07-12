package ru.nikpanfilov.delivery.shared.deliveryinfo.domain.entity

data class Address(
	val street: String,
	val house: String,
	val apartment: String,
	val courierNote: String,
)
