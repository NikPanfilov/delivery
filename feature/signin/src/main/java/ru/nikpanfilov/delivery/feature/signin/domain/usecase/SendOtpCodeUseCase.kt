package ru.nikpanfilov.delivery.feature.signin.domain.usecase

import ru.nikpanfilov.delivery.feature.signin.domain.repository.SignInRepository
import javax.inject.Inject

class SendOtpCodeUseCase @Inject constructor(
	private val repository: SignInRepository,
) : suspend (String) -> Double by repository::sendCode