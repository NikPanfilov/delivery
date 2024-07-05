package ru.nikpanfilov.delivery.core.token.domain.usecase

import ru.nikpanfilov.delivery.core.token.domain.repository.TokenRepository
import javax.inject.Inject

class ClearTokenUseCase @Inject constructor(
	private val repository: TokenRepository,
) : () -> Unit by repository::clearToken