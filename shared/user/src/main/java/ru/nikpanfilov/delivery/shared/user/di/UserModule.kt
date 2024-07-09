package ru.nikpanfilov.delivery.shared.user.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import ru.nikpanfilov.delivery.core.network.createApi
import ru.nikpanfilov.delivery.shared.user.data.api.UserApi
import ru.nikpanfilov.delivery.shared.user.data.repository.UserRepositoryImpl
import ru.nikpanfilov.delivery.shared.user.domain.repository.UserRepository

@Module(includes = [UserBinds::class])
class UserModule {

	@Provides
	fun provideProfileApi(retrofit: Retrofit): UserApi = createApi(retrofit)
}

@Module
interface UserBinds {

	@Binds
	fun bindProfileRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository
}