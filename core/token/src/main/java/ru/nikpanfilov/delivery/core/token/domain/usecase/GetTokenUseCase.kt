package ru.nikpanfilov.delivery.core.token.domain.usecase

import ru.nikpanfilov.delivery.core.token.domain.repository.TokenRepository
import javax.inject.Inject

class GetTokenUseCase @Inject constructor(
	private val repository: TokenRepository,
) : () -> String by repository::getToken