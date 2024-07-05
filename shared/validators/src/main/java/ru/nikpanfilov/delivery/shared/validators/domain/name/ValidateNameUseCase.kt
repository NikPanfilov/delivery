package ru.nikpanfilov.delivery.shared.validators.domain.name

import ru.nikpanfilov.delivery.shared.validators.domain.ValidationState
import javax.inject.Inject

class ValidateNameUseCase @Inject constructor() {

	operator fun invoke(data: String, namePart: NamePart): NameValidationItem = NameValidationItem(
		data = data,
		namePart = namePart,
		validationState = when {
			data.isEmpty()                                 -> {
				if (namePart == NamePart.PATRONYMIC) {
					ValidationState.Valid
				} else {
					ValidationState.Invalid(NameInvalidState.EMPTY)
				}
			}

			NameValidator.containsDifferentAlphabets(data) -> {
				ValidationState.Invalid(NameInvalidState.DIFFERENT_ALPHABETS)
			}

			NameValidator.containsRestrictedSymbols(data)  -> {
				ValidationState.Invalid(NameInvalidState.INCORRECT_FORMAT)
			}

			NameValidator.isTooLong(data)                  -> {
				ValidationState.Invalid(NameInvalidState.TOO_LONG)
			}

			else                                           -> {
				ValidationState.Valid
			}
		}
	)

}