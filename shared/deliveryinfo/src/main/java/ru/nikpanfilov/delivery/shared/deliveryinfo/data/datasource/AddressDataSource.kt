package ru.nikpanfilov.delivery.shared.deliveryinfo.data.datasource

import android.content.SharedPreferences
import com.squareup.moshi.JsonAdapter
import ru.nikpanfilov.delivery.shared.deliveryinfo.data.dto.AddressDto
import javax.inject.Inject

interface AddressDataSource {

	fun saveSender(address: AddressDto)
	fun loadSender(): AddressDto?

	fun saveReceiver(address: AddressDto)
	fun loadReceiver(): AddressDto?
}

class AddressDataSourceImpl @Inject constructor(
	private val sharedPreferences: SharedPreferences,
	private val adapter: JsonAdapter<AddressDto>,
) : AddressDataSource {

	companion object {

		internal const val ADDRESS_PREFS = "AddressPreferences"

		private const val SENDER_KEY = "SenderAddressDto"
		private const val RECEIVER_KEY = "ReceiverAddressDto"
	}

	override fun saveSender(address: AddressDto) {
		save(address, SENDER_KEY)
	}

	override fun loadSender(): AddressDto? = load(SENDER_KEY)

	override fun saveReceiver(address: AddressDto) {
		save(address, RECEIVER_KEY)
	}

	override fun loadReceiver(): AddressDto? = load(RECEIVER_KEY)

	private fun save(data: AddressDto, key: String) {
		val json = adapter.toJson(data)
		sharedPreferences.edit().putString(key, json).apply()
	}

	private fun load(key: String): AddressDto? {
		val json = sharedPreferences.getString(key, null)
		return json?.let { adapter.fromJson(it) }
	}
}