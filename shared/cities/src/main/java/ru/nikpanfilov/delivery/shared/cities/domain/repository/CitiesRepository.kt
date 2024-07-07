package ru.nikpanfilov.delivery.shared.cities.domain.repository

import ru.nikpanfilov.delivery.shared.cities.domain.entity.DeliveryPoint

interface CitiesRepository {

	suspend fun getCities(): List<DeliveryPoint>
}