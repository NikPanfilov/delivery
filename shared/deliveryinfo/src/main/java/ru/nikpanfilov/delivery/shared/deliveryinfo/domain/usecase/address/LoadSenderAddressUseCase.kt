package ru.nikpanfilov.delivery.shared.deliveryinfo.domain.usecase.address

import ru.nikpanfilov.delivery.shared.deliveryinfo.domain.entity.Address
import ru.nikpanfilov.delivery.shared.deliveryinfo.domain.repository.AddressRepository
import javax.inject.Inject

class LoadSenderAddressUseCase @Inject constructor(
	private val repository: AddressRepository,
) : () -> Address? by repository::loadSender