package ru.nikpanfilov.delivery.shared.deliveryinfo.domain.repository

import ru.nikpanfilov.delivery.shared.deliveryinfo.domain.entity.DeliveryOption

interface DeliveryOptionRepository {

	fun save(deliveryOption: DeliveryOption)

	fun load(): DeliveryOption
}