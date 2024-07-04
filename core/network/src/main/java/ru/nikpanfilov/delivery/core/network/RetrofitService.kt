package ru.nikpanfilov.delivery.core.network

import retrofit2.Retrofit

inline fun <reified T> createApi(retrofit: Retrofit): T =
	retrofit.create(T::class.java)