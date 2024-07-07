package ru.nikpanfilov.delivery.shared.deliveryinfo.domain.entity

data class DeliveryOption(
	val id: String,
	val price: Double,
	val days: Double,
	val name: String,
	val type: DeliveryType,
)

enum class DeliveryType {
	DEFAULT,
	EXPRESS,
}