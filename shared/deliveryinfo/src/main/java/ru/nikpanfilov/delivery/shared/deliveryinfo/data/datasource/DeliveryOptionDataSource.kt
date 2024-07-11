package ru.nikpanfilov.delivery.shared.deliveryinfo.data.datasource

import android.content.SharedPreferences
import com.squareup.moshi.JsonAdapter
import ru.nikpanfilov.delivery.shared.deliveryinfo.data.dto.DeliveryOptionDto
import javax.inject.Inject

interface DeliveryOptionDataSource {

	fun save(deliveryOption: DeliveryOptionDto)

	fun load(): DeliveryOptionDto
}

class DeliveryOptionDataSourceImpl @Inject constructor(
	private val sharedPreferences: SharedPreferences,
	private val adapter: JsonAdapter<DeliveryOptionDto>,
) : DeliveryOptionDataSource {

	companion object {

		internal const val DELIVERY_OPTIONS_PREFS = "DeliveryOptionPreferences"
		private const val KEY = "DeliveryOptionDto"
	}

	override fun save(deliveryOption: DeliveryOptionDto) {
		val jsonObject = adapter.toJson(deliveryOption)
		sharedPreferences.edit().putString(KEY, jsonObject).apply()
	}

	override fun load(): DeliveryOptionDto {
		val jsonObject = sharedPreferences.getString(KEY, null)
		return adapter.fromJson(jsonObject) ?: throw IllegalStateException("Способ доставки не выбран")
	}
}