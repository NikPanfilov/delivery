package ru.nikpanfilov.delivery.shared.deliveryinfo.domain.usecase

import ru.nikpanfilov.delivery.shared.deliveryinfo.domain.entity.PersonalInfo
import ru.nikpanfilov.delivery.shared.deliveryinfo.domain.repository.PersonalInfoRepository
import javax.inject.Inject

class LoadSenderInfoUseCase @Inject constructor(
	private val repository: PersonalInfoRepository,
) : () -> PersonalInfo? by repository::loadSender