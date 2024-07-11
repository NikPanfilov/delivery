package ru.nikpanfilov.delivery.shared.deliveryinfo.domain.repository

import ru.nikpanfilov.delivery.shared.deliveryinfo.domain.entity.PersonalInfo

interface PersonalInfoRepository {

	fun saveSender(info: PersonalInfo)

	fun loadSender(): PersonalInfo?

	fun saveReceiver(info: PersonalInfo)

	fun loadReceiver(): PersonalInfo?
}