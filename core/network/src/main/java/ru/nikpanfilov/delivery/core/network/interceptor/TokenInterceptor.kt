package ru.nikpanfilov.delivery.core.network.interceptor

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import ru.nikpanfilov.delivery.core.token.domain.usecase.GetTokenUseCase
import ru.nikpanfilov.delivery.core.token.domain.usecase.IsTokenExistUseCase
import javax.inject.Inject

class TokenInterceptor @Inject constructor(
	private val getTokenUseCase: GetTokenUseCase,
	private val isTokenExistUseCase: IsTokenExistUseCase
) : Interceptor {

	internal companion object {

		const val TOKEN_HEADER = "Authorization"
	}

	override fun intercept(chain: Interceptor.Chain): Response {
		if (isTokenExistUseCase()) {
			val requestBuilder: Request.Builder = chain.request().newBuilder()
				.addHeader(TOKEN_HEADER, getTokenUseCase())
			val request: Request = requestBuilder.build()

			return chain.proceed(request)
		}

		return chain.proceed(chain.request())
	}
}