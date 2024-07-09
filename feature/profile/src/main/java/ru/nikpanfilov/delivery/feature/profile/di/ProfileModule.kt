package ru.nikpanfilov.delivery.feature.profile.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import ru.nikpanfilov.delivery.core.network.createApi
import ru.nikpanfilov.delivery.feature.profile.data.api.ProfileApi
import ru.nikpanfilov.delivery.feature.profile.data.repository.ProfileRepositoryImpl
import ru.nikpanfilov.delivery.feature.profile.domain.repository.ProfileRepository

@Module(includes = [ProfileBinds::class])
class ProfileModule {

	@Provides
	fun provideProfileApi(retrofit: Retrofit): ProfileApi = createApi(retrofit)
}

@Module
interface ProfileBinds {

	@Binds
	fun bindProfileRepository(profileRepositoryImpl: ProfileRepositoryImpl): ProfileRepository
}