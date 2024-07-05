package ru.nikpanfilov.delivery.shared.validators.domain.courier

import ru.nikpanfilov.delivery.shared.validators.domain.DefaultValidationItem
import ru.nikpanfilov.delivery.shared.validators.domain.ValidationState
import javax.inject.Inject

class ValidateCourierUseCase @Inject constructor() {

	operator fun invoke(data: String): DefaultValidationItem = DefaultValidationItem(
		data = data,
		optional = true,
		validationState = when {
			CourierValidator.isTooLong(data) -> ValidationState.Invalid(CourierInvalidState.TOO_LONG)
			else                             -> ValidationState.Valid
		}
	)
}