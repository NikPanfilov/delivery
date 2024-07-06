package ru.nikpanfilov.delivery.shared.cities.data.repository

import ru.nikpanfilov.delivery.shared.cities.data.api.CitiesApi
import ru.nikpanfilov.delivery.shared.cities.data.mapper.toDeliveryPoints
import ru.nikpanfilov.delivery.shared.cities.domain.entity.DeliveryPoint
import ru.nikpanfilov.delivery.shared.cities.domain.repository.CitiesRepository
import javax.inject.Inject

class CitiesRepositoryImpl @Inject constructor(
	private val api: CitiesApi,
) : CitiesRepository {

	override suspend fun getCities(): List<DeliveryPoint> =
		api.getCities().toDeliveryPoints()
}