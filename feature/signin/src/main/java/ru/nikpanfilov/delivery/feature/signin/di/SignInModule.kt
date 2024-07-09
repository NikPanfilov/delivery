package ru.nikpanfilov.delivery.feature.signin.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import ru.nikpanfilov.delivery.core.network.createApi
import ru.nikpanfilov.delivery.feature.signin.data.api.SignInApi
import ru.nikpanfilov.delivery.feature.signin.data.repository.SignInRepositoryImpl
import ru.nikpanfilov.delivery.feature.signin.domain.repository.SignInRepository

@Module(includes = [SignInBinds::class])
class SignInModule {

	@Provides
	fun provideSignInApi(retrofit: Retrofit): SignInApi = createApi(retrofit)
}

@Module
interface SignInBinds {

	@Binds
	fun bindSignInRepository(signInRepositoryImpl: SignInRepositoryImpl): SignInRepository
}