package ru.nikpanfilov.delivery.core.network.providers

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

internal fun createOkHttp(
	interceptors: List<Interceptor>,
): OkHttpClient = OkHttpClient()
	.newBuilder()
	.applyDefaultSetups(interceptors)
	.build()

private const val VALUE_TIMEOUT: Long = 60

private fun OkHttpClient.Builder.applyDefaultSetups(
	interceptors: List<Interceptor>,
): OkHttpClient.Builder {
	connectTimeout(VALUE_TIMEOUT, TimeUnit.SECONDS)
	writeTimeout(VALUE_TIMEOUT, TimeUnit.SECONDS)
	readTimeout(VALUE_TIMEOUT, TimeUnit.SECONDS)
	retryOnConnectionFailure(true)
	interceptors.forEach { addInterceptor(it) }
	return this
}