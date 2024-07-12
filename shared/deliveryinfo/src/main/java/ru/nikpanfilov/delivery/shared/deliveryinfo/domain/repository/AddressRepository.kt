package ru.nikpanfilov.delivery.shared.deliveryinfo.domain.repository

import ru.nikpanfilov.delivery.shared.deliveryinfo.domain.entity.Address

interface AddressRepository {

	fun saveSender(address: Address)
	fun loadSender(): Address?

	fun saveReceiver(address: Address)
	fun loadReceiver(): Address?
}