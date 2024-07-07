package ru.nikpanfilov.delivery.feature.calculation.domain.repository

import ru.nikpanfilov.delivery.feature.calculation.domain.entity.PackageType

interface PackageTypeRepository {

	suspend fun getTypes(): List<PackageType>
}