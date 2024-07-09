package ru.nikpanfilov.delivery.feature.signin.data.api

import retrofit2.http.Body
import retrofit2.http.POST
import ru.nikpanfilov.delivery.feature.signin.data.dto.CreateOtpDto
import ru.nikpanfilov.delivery.feature.signin.data.dto.OtpResponseDto
import ru.nikpanfilov.delivery.feature.signin.data.dto.SignInDto
import ru.nikpanfilov.delivery.feature.signin.data.dto.SignInResponseDto

interface SignInApi {

	@POST("/auth/otp")
	suspend fun sendCode(@Body phone: CreateOtpDto): OtpResponseDto

	@POST("/users/signin")
	suspend fun signIn(@Body signInDto: SignInDto): SignInResponseDto
}