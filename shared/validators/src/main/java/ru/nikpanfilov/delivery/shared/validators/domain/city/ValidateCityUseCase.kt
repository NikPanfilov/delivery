package ru.nikpanfilov.delivery.shared.validators.domain.city

import ru.nikpanfilov.delivery.shared.validators.domain.DefaultValidationItem
import ru.nikpanfilov.delivery.shared.validators.domain.ValidationState
import javax.inject.Inject

class ValidateCityUseCase @Inject constructor() {

	operator fun invoke(data: String): DefaultValidationItem = DefaultValidationItem(
		data = data,
		validationState = when {
			data.isEmpty()                                 -> ValidationState.Invalid(CityInvalidState.EMPTY)
			CityValidator.containsDifferentAlphabets(data) -> ValidationState.Invalid(CityInvalidState.DIFFERENT_ALPHABETS)
			CityValidator.containsRestrictedSymbols(data)  -> ValidationState.Invalid(CityInvalidState.INCORRECT_FORMAT)
			CityValidator.isTooLong(data)                  -> ValidationState.Invalid(CityInvalidState.TOO_LONG)
			else                                           -> ValidationState.Valid
		}
	)

}