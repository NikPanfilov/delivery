package ru.nikpanfilov.delivery.shared.validators.domain.address

import ru.nikpanfilov.delivery.shared.validators.domain.ValidationState

data class AddressValidationItem(
	val data: String = "",
	val addressPart: AddressPart,
	val validationState: ValidationState = ValidationState.Unverified,
)

fun AddressValidationItem.isValid(): Boolean = if (addressPart == AddressPart.APARTMENT) {
	this.validationState !is ValidationState.Invalid
} else {
	this.validationState is ValidationState.Valid
}
