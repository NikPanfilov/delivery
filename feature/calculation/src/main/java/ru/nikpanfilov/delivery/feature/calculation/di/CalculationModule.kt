package ru.nikpanfilov.delivery.feature.calculation.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import ru.nikpanfilov.delivery.core.network.createApi
import ru.nikpanfilov.delivery.feature.calculation.data.api.CalculationApi
import ru.nikpanfilov.delivery.feature.calculation.data.api.PackageTypeApi
import ru.nikpanfilov.delivery.feature.calculation.data.repository.CalculationRepositoryImpl
import ru.nikpanfilov.delivery.feature.calculation.data.repository.PackageTypeRepositoryImpl
import ru.nikpanfilov.delivery.feature.calculation.domain.repository.CalculationRepository
import ru.nikpanfilov.delivery.feature.calculation.domain.repository.PackageTypeRepository

@Module(includes = [CalculationBinds::class])
class CalculationModule {

	@Provides
	fun providePackageTypeApi(retrofit: Retrofit): PackageTypeApi = createApi(retrofit)

	@Provides
	fun provideCalculationApi(retrofit: Retrofit): CalculationApi = createApi(retrofit)
}

@Module
interface CalculationBinds {

	@Binds
	fun bindPackageTypeRepository(packageTypeRepositoryImpl: PackageTypeRepositoryImpl): PackageTypeRepository

	@Binds
	fun bindCalculationRepository(calculationRepositoryImpl: CalculationRepositoryImpl): CalculationRepository
}