package ru.nikpanfilov.delivery.shared.validators.domain.phone

internal object PhoneValidator {

	private const val VALID_PHONE_REGEX = "^(\\+)?\\d+\$"

	fun containsRestrictedSymbols(phone: String): Boolean = !phone.matches(Regex(VALID_PHONE_REGEX))
}