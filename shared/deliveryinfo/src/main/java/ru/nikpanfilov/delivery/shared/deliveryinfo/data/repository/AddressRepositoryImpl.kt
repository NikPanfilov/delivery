package ru.nikpanfilov.delivery.shared.deliveryinfo.data.repository

import ru.nikpanfilov.delivery.shared.deliveryinfo.data.datasource.AddressDataSource
import ru.nikpanfilov.delivery.shared.deliveryinfo.data.mapper.toDto
import ru.nikpanfilov.delivery.shared.deliveryinfo.data.mapper.toEntity
import ru.nikpanfilov.delivery.shared.deliveryinfo.domain.entity.Address
import ru.nikpanfilov.delivery.shared.deliveryinfo.domain.repository.AddressRepository
import javax.inject.Inject

class AddressRepositoryImpl @Inject constructor(
	private val dataSource: AddressDataSource,
) : AddressRepository {

	override fun saveSender(address: Address) {
		dataSource.saveSender(address.toDto())
	}

	override fun loadSender(): Address? =
		dataSource.loadSender()?.toEntity()

	override fun saveReceiver(address: Address) {
		dataSource.saveReceiver(address.toDto())
	}

	override fun loadReceiver(): Address? =
		dataSource.loadReceiver()?.toEntity()
}