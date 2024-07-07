package ru.nikpanfilov.delivery.feature.calculation.domain.usecase

import ru.nikpanfilov.delivery.feature.calculation.domain.entity.PackageType
import ru.nikpanfilov.delivery.feature.calculation.domain.repository.PackageTypeRepository
import javax.inject.Inject

class GetPackageTypesUseCase @Inject constructor(
	private val repository: PackageTypeRepository,
) : suspend () -> List<PackageType> by repository::getTypes