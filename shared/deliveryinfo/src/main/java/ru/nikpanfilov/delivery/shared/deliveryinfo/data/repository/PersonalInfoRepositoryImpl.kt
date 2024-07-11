package ru.nikpanfilov.delivery.shared.deliveryinfo.data.repository

import ru.nikpanfilov.delivery.shared.deliveryinfo.data.datasource.PersonalInfoDataSource
import ru.nikpanfilov.delivery.shared.deliveryinfo.data.mapper.toDto
import ru.nikpanfilov.delivery.shared.deliveryinfo.data.mapper.toEntity
import ru.nikpanfilov.delivery.shared.deliveryinfo.domain.entity.PersonalInfo
import ru.nikpanfilov.delivery.shared.deliveryinfo.domain.repository.PersonalInfoRepository
import javax.inject.Inject

class PersonalInfoRepositoryImpl @Inject constructor(
	private val dataSource: PersonalInfoDataSource,
) : PersonalInfoRepository {

	override fun saveSender(info: PersonalInfo) {
		dataSource.saveSender(info.toDto())
	}

	override fun loadSender(): PersonalInfo? =
		dataSource.loadSender()?.toEntity()

	override fun saveReceiver(info: PersonalInfo) {
		dataSource.saveReceiver(info.toDto())
	}

	override fun loadReceiver(): PersonalInfo? =
		dataSource.loadReceiver()?.toEntity()
}