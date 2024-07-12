package ru.nikpanfilov.delivery.shared.deliveryinfo.domain.usecase.address

import ru.nikpanfilov.delivery.shared.deliveryinfo.domain.entity.Address
import ru.nikpanfilov.delivery.shared.deliveryinfo.domain.repository.AddressRepository
import javax.inject.Inject

class SaveReceiverAddressUseCase @Inject constructor(
	private val repository: AddressRepository,
) : (Address) -> Unit by repository::saveReceiver