package ru.nikpanfilov.delivery.shared.cities.domain.usecase

import ru.nikpanfilov.delivery.shared.cities.domain.entity.DeliveryPoint
import ru.nikpanfilov.delivery.shared.cities.domain.repository.CitiesRepository
import javax.inject.Inject

class GetDeliveryCitiesUseCase @Inject constructor(
	private val repository: CitiesRepository,
) : suspend () -> List<DeliveryPoint> by repository::getCities