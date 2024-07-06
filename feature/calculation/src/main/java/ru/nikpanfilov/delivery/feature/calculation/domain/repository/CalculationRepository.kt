package ru.nikpanfilov.delivery.feature.calculation.domain.repository

import ru.nikpanfilov.delivery.feature.calculation.domain.entity.CalculateDelivery
import ru.nikpanfilov.delivery.shared.deliveryinfo.domain.entity.DeliveryOption

interface CalculationRepository {

	suspend fun calculate(calculateDelivery: CalculateDelivery): List<DeliveryOption>
}