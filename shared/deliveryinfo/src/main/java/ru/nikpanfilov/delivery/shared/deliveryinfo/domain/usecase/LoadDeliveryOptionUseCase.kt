package ru.nikpanfilov.delivery.shared.deliveryinfo.domain.usecase

import ru.nikpanfilov.delivery.shared.deliveryinfo.domain.entity.DeliveryOption
import ru.nikpanfilov.delivery.shared.deliveryinfo.domain.repository.DeliveryOptionRepository
import javax.inject.Inject

class LoadDeliveryOptionUseCase @Inject constructor(
	private val repository: DeliveryOptionRepository,
) : () -> DeliveryOption by repository::load