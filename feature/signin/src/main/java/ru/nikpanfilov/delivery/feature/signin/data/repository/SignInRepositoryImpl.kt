package ru.nikpanfilov.delivery.feature.signin.data.repository

import ru.nikpanfilov.delivery.core.token.data.datasource.LocalTokenDataSource
import ru.nikpanfilov.delivery.feature.signin.data.api.SignInApi
import ru.nikpanfilov.delivery.feature.signin.data.dto.CreateOtpDto
import ru.nikpanfilov.delivery.feature.signin.data.mapper.toDto
import ru.nikpanfilov.delivery.feature.signin.domain.entity.SignIn
import ru.nikpanfilov.delivery.feature.signin.domain.repository.SignInRepository
import javax.inject.Inject

class SignInRepositoryImpl @Inject constructor(
	private val api: SignInApi,
	private val tokenDataSource: LocalTokenDataSource,
) : SignInRepository {

	override suspend fun sendCode(phone: String): Double =
		api.sendCode(CreateOtpDto(phone)).retryDelay

	override suspend fun signIn(signIn: SignIn) {
		val token = api.signIn(signIn.toDto()).token
		tokenDataSource.saveToken(token)
	}
}