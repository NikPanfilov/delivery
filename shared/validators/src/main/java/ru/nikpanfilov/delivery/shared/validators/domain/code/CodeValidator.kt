package ru.nikpanfilov.delivery.shared.validators.domain.code

internal object CodeValidator {

	private const val VALID_CODE_REGEX = "^\\d+\$"
	private const val VALID_LENGTH = 6

	fun containsRestrictedSymbols(code: String): Boolean = !code.matches(Regex(VALID_CODE_REGEX))

	fun isIncorrectLength(code: String): Boolean = code.length != VALID_LENGTH
}