package ru.nikpanfilov.delivery.feature.calculation.data.repository

import ru.nikpanfilov.delivery.feature.calculation.data.api.CalculationApi
import ru.nikpanfilov.delivery.feature.calculation.data.mapper.toDto
import ru.nikpanfilov.delivery.feature.calculation.domain.entity.CalculateDelivery
import ru.nikpanfilov.delivery.feature.calculation.domain.repository.CalculationRepository
import ru.nikpanfilov.delivery.shared.deliveryinfo.data.mapper.toEntity
import ru.nikpanfilov.delivery.shared.deliveryinfo.domain.entity.DeliveryOption
import javax.inject.Inject

class CalculationRepositoryImpl @Inject constructor(
	private val api: CalculationApi,
) : CalculationRepository {

	override suspend fun calculate(calculateDelivery: CalculateDelivery): List<DeliveryOption> =
		api.calculate(calculateDelivery.toDto()).options.toEntity()
}