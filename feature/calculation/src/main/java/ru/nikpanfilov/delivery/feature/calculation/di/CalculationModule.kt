package ru.nikpanfilov.delivery.feature.calculation.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import ru.nikpanfilov.delivery.core.network.createApi
import ru.nikpanfilov.delivery.feature.calculation.data.api.PackageTypeApi
import ru.nikpanfilov.delivery.feature.calculation.data.repository.PackageTypeRepositoryImpl
import ru.nikpanfilov.delivery.feature.calculation.domain.repository.PackageTypeRepository

@Module(includes = [CalculationBinds::class])
class CalculationModule {

	@Provides
	fun providePackageTypeApi(retrofit: Retrofit): PackageTypeApi = createApi(retrofit)
}

@Module
interface CalculationBinds {

	@Binds
	fun bindPackageTypeRepository(packageTypeRepositoryImpl: PackageTypeRepositoryImpl): PackageTypeRepository
}