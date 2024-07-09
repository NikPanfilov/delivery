package ru.nikpanfilov.delivery.feature.signin.domain.repository

import ru.nikpanfilov.delivery.feature.signin.domain.entity.SignIn

interface SignInRepository {

	suspend fun sendCode(phone: String): Double

	suspend fun signIn(signIn: SignIn)
}