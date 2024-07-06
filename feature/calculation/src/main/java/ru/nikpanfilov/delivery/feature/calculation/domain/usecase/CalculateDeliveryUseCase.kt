package ru.nikpanfilov.delivery.feature.calculation.domain.usecase

import ru.nikpanfilov.delivery.feature.calculation.domain.entity.CalculateDelivery
import ru.nikpanfilov.delivery.feature.calculation.domain.repository.CalculationRepository
import ru.nikpanfilov.delivery.shared.deliveryinfo.domain.entity.DeliveryOption
import javax.inject.Inject

class CalculateDeliveryUseCase @Inject constructor(
	private val repository: CalculationRepository,
) : suspend (CalculateDelivery) -> List<DeliveryOption> by repository::calculate