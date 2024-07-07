package ru.nikpanfilov.delivery.feature.calculation.data.repository

import ru.nikpanfilov.delivery.feature.calculation.data.api.PackageTypeApi
import ru.nikpanfilov.delivery.feature.calculation.data.mapper.toPackageTypes
import ru.nikpanfilov.delivery.feature.calculation.domain.entity.PackageType
import ru.nikpanfilov.delivery.feature.calculation.domain.repository.PackageTypeRepository
import javax.inject.Inject

class PackageTypeRepositoryImpl @Inject constructor(
	private val api: PackageTypeApi,
) : PackageTypeRepository {

	override suspend fun getTypes(): List<PackageType> =
		api.getTypes().toPackageTypes()
}