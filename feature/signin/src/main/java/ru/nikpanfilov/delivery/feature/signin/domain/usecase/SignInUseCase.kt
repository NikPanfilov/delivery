package ru.nikpanfilov.delivery.feature.signin.domain.usecase

import ru.nikpanfilov.delivery.feature.signin.domain.entity.SignIn
import ru.nikpanfilov.delivery.feature.signin.domain.repository.SignInRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(
	private val repository: SignInRepository,
) : suspend (SignIn) -> Unit by repository::signIn