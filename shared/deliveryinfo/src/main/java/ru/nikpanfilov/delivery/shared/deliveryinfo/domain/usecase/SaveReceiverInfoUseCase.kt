package ru.nikpanfilov.delivery.shared.deliveryinfo.domain.usecase

import ru.nikpanfilov.delivery.shared.deliveryinfo.domain.entity.PersonalInfo
import ru.nikpanfilov.delivery.shared.deliveryinfo.domain.repository.PersonalInfoRepository
import javax.inject.Inject

class SaveReceiverInfoUseCase @Inject constructor(
	private val repository: PersonalInfoRepository,
) : (PersonalInfo) -> Unit by repository::saveReceiver