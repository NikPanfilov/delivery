package ru.nikpanfilov.delivery.core.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Inject

class LoggingInterceptor @Inject constructor() : Interceptor {

	override fun intercept(chain: Interceptor.Chain): Response =
		HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY).intercept(chain)
}