package ru.nikpanfilov.delivery.shared.deliveryinfo.data.datasource

import android.content.SharedPreferences
import com.squareup.moshi.JsonAdapter
import ru.nikpanfilov.delivery.shared.deliveryinfo.data.dto.PersonalInfoDto
import javax.inject.Inject

interface PersonalInfoDataSource {

	fun saveSender(info: PersonalInfoDto)

	fun loadSender(): PersonalInfoDto?

	fun saveReceiver(info: PersonalInfoDto)

	fun loadReceiver(): PersonalInfoDto?
}

class PersonalInfoDataSourceImpl @Inject constructor(
	private val sharedPreferences: SharedPreferences,
	private val adapter: JsonAdapter<PersonalInfoDto>,
) : PersonalInfoDataSource {

	companion object {

		internal const val PERSONAL_INFO_PREFS = "PersonalInfoPreferences"

		private const val SENDER_KEY = "SenderPersonalInfoDto"
		private const val RECEIVER_KEY = "ReceiverPersonalInfoDto"
	}

	override fun saveSender(info: PersonalInfoDto) {
		save(info, SENDER_KEY)
	}

	override fun loadSender(): PersonalInfoDto? = load(SENDER_KEY)

	override fun saveReceiver(info: PersonalInfoDto) {
		save(info, RECEIVER_KEY)
	}

	override fun loadReceiver(): PersonalInfoDto? = load(RECEIVER_KEY)

	private fun save(data: PersonalInfoDto, key: String) {
		val json = adapter.toJson(data)
		sharedPreferences.edit().putString(key, json).apply()
	}

	private fun load(key: String): PersonalInfoDto? {
		val json = sharedPreferences.getString(key, null)
		return json?.let { adapter.fromJson(it) }
	}
}