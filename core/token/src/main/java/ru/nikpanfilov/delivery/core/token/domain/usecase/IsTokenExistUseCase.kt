package ru.nikpanfilov.delivery.core.token.domain.usecase

import ru.nikpanfilov.delivery.core.token.domain.repository.TokenRepository
import javax.inject.Inject

class IsTokenExistUseCase @Inject constructor(
	private val repository: TokenRepository,
) : () -> Boolean by repository::isTokenExist