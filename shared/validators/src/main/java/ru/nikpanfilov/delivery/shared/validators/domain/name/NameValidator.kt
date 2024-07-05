package ru.nikpanfilov.delivery.shared.validators.domain.name

internal object NameValidator {

	private const val RUS_SYMBOLS = "а-яА-ЯёЁ"
	private const val ENG_SYMBOLS = "a-zA-Z"
	private const val VALID_RUS_NAME_REGEX_STRING = "^[а-яё]([\\-\\s`‘]?[а-яё]+)*$"
	private const val VALID_ENG_NAME_REGEX_STRING = "^[a-z]([\\-\\s`‘]?[a-z]+)*$"
	private const val MAX_LENGTH = 60

	fun containsDifferentAlphabets(name: String): Boolean = name.contains(RUS_SYMBOLS) && name.contains(ENG_SYMBOLS)

	fun containsRestrictedSymbols(name: String): Boolean =
		!name.matches(Regex(VALID_RUS_NAME_REGEX_STRING, RegexOption.IGNORE_CASE)) &&
			!name.matches(Regex(VALID_ENG_NAME_REGEX_STRING, RegexOption.IGNORE_CASE))

	fun isTooLong(name: String): Boolean = name.length > MAX_LENGTH
}