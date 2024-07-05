package ru.nikpanfilov.delivery.shared.validators.domain.courier

internal object CourierValidator {

	private const val VALID_LENGTH = 300

	fun isTooLong(data: String): Boolean = data.length > VALID_LENGTH
}