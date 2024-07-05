package ru.nikpanfilov.delivery.shared.validators.domain.address

import ru.nikpanfilov.delivery.shared.validators.domain.ValidationState
import javax.inject.Inject

class ValidateAddressUseCase @Inject constructor() {

	operator fun invoke(data: String, addressPart: AddressPart): AddressValidationItem = AddressValidationItem(
		data = data,
		addressPart = addressPart,
		validationState = when {
			data.isEmpty()                                    -> {
				if (addressPart == AddressPart.APARTMENT) {
					ValidationState.Valid
				} else {
					ValidationState.Invalid(AddressInvalidState.EMPTY)
				}
			}

			AddressValidator.containsDifferentAlphabets(data) -> {
				ValidationState.Invalid(AddressInvalidState.DIFFERENT_ALPHABETS)
			}

			AddressValidator.containsRestrictedSymbols(data)  -> {
				ValidationState.Invalid(AddressInvalidState.INCORRECT_FORMAT)
			}

			AddressValidator.isTooLong(data)                  -> {
				ValidationState.Invalid(AddressInvalidState.TOO_LONG)
			}

			else                                              -> {
				ValidationState.Valid
			}
		}
	)
}