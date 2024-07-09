package ru.nikpanfilov.delivery.feature.profile.domain.usecase

import ru.nikpanfilov.delivery.feature.profile.domain.repository.ProfileRepository
import ru.nikpanfilov.delivery.shared.user.domain.entity.User
import javax.inject.Inject

class UpdateUserUseCase @Inject constructor(
	private val repository: ProfileRepository,
) : suspend (User) -> Unit by repository::update