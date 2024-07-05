package ru.nikpanfilov.delivery.shared.validators.domain.email

internal object EmailValidator {

	private const val VALID_EMAIL_REGEX_STRING = "^([a-z\\d]\\.?[_-]*)+[a-z\\d]@([a-z\\d]([-*]?[a-z\\d])*\\.)+[a-z\\d]+$"

	fun isInvalid(email: String): Boolean = !email.matches(Regex(VALID_EMAIL_REGEX_STRING, RegexOption.IGNORE_CASE))
}