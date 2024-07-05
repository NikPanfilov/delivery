package ru.nikpanfilov.delivery.shared.validators.domain.email

import ru.nikpanfilov.delivery.shared.validators.domain.DefaultValidationItem
import ru.nikpanfilov.delivery.shared.validators.domain.ValidationState
import javax.inject.Inject

class ValidateEmailUseCase @Inject constructor() {

	operator fun invoke(data: String): DefaultValidationItem = DefaultValidationItem(
		data = data,
		validationState = when {
			data.isEmpty()                 -> ValidationState.Invalid(EmailInvalidState.EMPTY)
			EmailValidator.isInvalid(data) -> ValidationState.Invalid(EmailInvalidState.INCORRECT_FORMAT)
			else                           -> ValidationState.Valid
		}
	)
}