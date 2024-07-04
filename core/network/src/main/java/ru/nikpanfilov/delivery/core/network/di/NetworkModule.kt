package ru.nikpanfilov.delivery.core.network.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import ru.nikpanfilov.delivery.core.network.interceptor.LoggingInterceptor
import ru.nikpanfilov.delivery.core.network.interceptor.TokenInterceptor
import ru.nikpanfilov.delivery.core.network.providers.createOkHttp
import ru.nikpanfilov.delivery.core.network.providers.createRetrofit
import java.util.Date
import javax.inject.Singleton

@Module
object NetworkModule {

	private const val BASE_URL = "https://shift-backend.onrender.com"

	@Provides
	@Singleton
	fun provideOkHttpClient(
		tokenInterceptor: TokenInterceptor,
		loggingInterceptor: LoggingInterceptor,
	): OkHttpClient = createOkHttp(
		interceptors = listOf(tokenInterceptor, loggingInterceptor),
	)

	@Provides
	fun provideMoshi(): Moshi = Moshi.Builder()
		.add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
		.add(KotlinJsonAdapterFactory())
		.build()

	@Provides
	@Singleton
	fun provideRetrofit(
		okHttpClient: OkHttpClient,
		moshi: Moshi,
	): Retrofit = createRetrofit(
		okHttpClient = okHttpClient,
		moshi = moshi,
		url = BASE_URL,
	)
}