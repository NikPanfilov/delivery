package ru.nikpanfilov.delivery.feature.calculation.data.mapper

import ru.nikpanfilov.delivery.feature.calculation.data.dto.DeliveryPackageTypeResponseDto
import ru.nikpanfilov.delivery.feature.calculation.data.dto.PackageTypeDto
import ru.nikpanfilov.delivery.feature.calculation.domain.entity.PackageType

internal fun DeliveryPackageTypeResponseDto.toPackageTypes(): List<PackageType> =
	this.packages.map(PackageTypeDto::toEntity)

private fun PackageTypeDto.toEntity(): PackageType = PackageType(
	id = id,
	name = name,
	length = length,
	width = width,
	height = height,
	weight = weight,
)

internal fun PackageType.toDto(): PackageTypeDto = PackageTypeDto(
	id = id,
	name = name,
	length = length,
	width = width,
	height = height,
	weight = weight,
)