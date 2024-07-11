package ru.nikpanfilov.delivery.feature.shippingmethod

import kotlinx.serialization.Serializable
import ru.nikpanfilov.delivery.core.navigation.Destination

@Serializable
data class ShippingMethodDestination(
	val deliveryOptions: String,
) : Destination