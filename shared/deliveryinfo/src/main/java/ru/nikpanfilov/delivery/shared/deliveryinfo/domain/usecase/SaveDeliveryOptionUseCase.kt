package ru.nikpanfilov.delivery.shared.deliveryinfo.domain.usecase

import ru.nikpanfilov.delivery.shared.deliveryinfo.domain.entity.DeliveryOption
import ru.nikpanfilov.delivery.shared.deliveryinfo.domain.repository.DeliveryOptionRepository
import javax.inject.Inject

class SaveDeliveryOptionUseCase @Inject constructor(
	private val repository: DeliveryOptionRepository,
) : (DeliveryOption) -> Unit by repository::save