package ru.nikpanfilov.delivery.shared.validators.domain.code

import ru.nikpanfilov.delivery.shared.validators.domain.DefaultValidationItem
import ru.nikpanfilov.delivery.shared.validators.domain.ValidationState
import javax.inject.Inject

class ValidateCodeUseCase @Inject constructor() {

	operator fun invoke(data: String): DefaultValidationItem = DefaultValidationItem(
		data = data,
		validationState = when {
			data.isEmpty()                                -> ValidationState.Invalid(CodeInvalidState.EMPTY)
			CodeValidator.containsRestrictedSymbols(data) -> ValidationState.Invalid(CodeInvalidState.RESTRICTED_SYMBOLS)
			CodeValidator.isIncorrectLength(data)         -> ValidationState.Invalid(CodeInvalidState.WRONG_LENGTH)
			else                                          -> ValidationState.Valid
		}
	)
}