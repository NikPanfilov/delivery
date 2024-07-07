package ru.nikpanfilov.delivery.shared.cities.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import ru.nikpanfilov.delivery.core.network.createApi
import ru.nikpanfilov.delivery.shared.cities.data.api.CitiesApi
import ru.nikpanfilov.delivery.shared.cities.data.repository.CitiesRepositoryImpl
import ru.nikpanfilov.delivery.shared.cities.domain.repository.CitiesRepository

@Module(includes = [CitiesBinds::class])
class CitiesModule {

	@Provides
	fun provideCitiesApi(retrofit: Retrofit): CitiesApi = createApi(retrofit)
}

@Module
interface CitiesBinds {

	@Binds
	fun bindCitiesRepository(citiesRepositoryImpl: CitiesRepositoryImpl): CitiesRepository
}