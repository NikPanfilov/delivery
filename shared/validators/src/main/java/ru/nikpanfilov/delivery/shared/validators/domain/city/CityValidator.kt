package ru.nikpanfilov.delivery.shared.validators.domain.city

internal object CityValidator {

	private const val RUS_SYMBOLS = "а-яА-ЯёЁ"
	private const val ENG_SYMBOLS = "a-zA-Z"
	private const val VALID_RUS_NAME_REGEX_STRING = "^[а-яё\\d]([\\-\\s,.]?[а-яё\\d]+)*$"
	private const val VALID_ENG_NAME_REGEX_STRING = "^[a-z]([\\-\\s,.]?[a-z]+)*$"
	private const val MAX_LENGTH = 60

	fun containsDifferentAlphabets(name: String): Boolean = name.contains(RUS_SYMBOLS) && name.contains(ENG_SYMBOLS)

	fun containsRestrictedSymbols(name: String): Boolean =
		!name.matches(Regex(VALID_RUS_NAME_REGEX_STRING, RegexOption.IGNORE_CASE)) &&
			!name.matches(Regex(VALID_ENG_NAME_REGEX_STRING, RegexOption.IGNORE_CASE))

	fun isTooLong(name: String): Boolean = name.length > MAX_LENGTH
}