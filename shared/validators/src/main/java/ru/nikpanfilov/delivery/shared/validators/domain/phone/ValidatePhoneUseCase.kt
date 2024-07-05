package ru.nikpanfilov.delivery.shared.validators.domain.phone

import ru.nikpanfilov.delivery.shared.validators.domain.DefaultValidationItem
import ru.nikpanfilov.delivery.shared.validators.domain.ValidationState
import javax.inject.Inject

class ValidatePhoneUseCase @Inject constructor() {

	operator fun invoke(data: String): DefaultValidationItem = DefaultValidationItem(
		data = data,
		validationState = when {
			data.isEmpty()                                 -> ValidationState.Invalid(PhoneInvalidState.EMPTY)
			PhoneValidator.containsRestrictedSymbols(data) -> ValidationState.Invalid(PhoneInvalidState.RESTRICTED_SYMBOLS)
			else                                           -> ValidationState.Valid
		}
	)
}