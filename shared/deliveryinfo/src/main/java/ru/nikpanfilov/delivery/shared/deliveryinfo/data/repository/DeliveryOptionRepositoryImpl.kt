package ru.nikpanfilov.delivery.shared.deliveryinfo.data.repository

import ru.nikpanfilov.delivery.shared.deliveryinfo.data.datasource.DeliveryOptionDataSource
import ru.nikpanfilov.delivery.shared.deliveryinfo.data.mapper.toDto
import ru.nikpanfilov.delivery.shared.deliveryinfo.data.mapper.toEntity
import ru.nikpanfilov.delivery.shared.deliveryinfo.domain.entity.DeliveryOption
import ru.nikpanfilov.delivery.shared.deliveryinfo.domain.repository.DeliveryOptionRepository
import javax.inject.Inject

class DeliveryOptionRepositoryImpl @Inject constructor(
	private val dataSource: DeliveryOptionDataSource,
) : DeliveryOptionRepository {

	override fun save(deliveryOption: DeliveryOption) {
		dataSource.save(deliveryOption.toDto())
	}

	override fun load(): DeliveryOption =
		dataSource.load().toEntity()
}