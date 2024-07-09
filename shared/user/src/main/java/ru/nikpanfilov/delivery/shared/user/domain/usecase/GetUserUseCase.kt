package ru.nikpanfilov.delivery.shared.user.domain.usecase

import ru.nikpanfilov.delivery.shared.user.domain.entity.User
import ru.nikpanfilov.delivery.shared.user.domain.repository.UserRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
	private val repository: UserRepository,
) : suspend () -> User by repository::get