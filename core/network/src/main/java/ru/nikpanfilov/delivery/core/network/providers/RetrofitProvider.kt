package ru.nikpanfilov.delivery.core.network.providers

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

internal fun createRetrofit(
	okHttpClient: OkHttpClient,
	moshi: Moshi,
	url: String,
): Retrofit = Retrofit.Builder()
	.addConverterFactory(ScalarsConverterFactory.create())
	.addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
	.client(okHttpClient)
	.baseUrl(url)
	.build()