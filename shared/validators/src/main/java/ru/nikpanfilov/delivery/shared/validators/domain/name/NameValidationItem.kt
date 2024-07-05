package ru.nikpanfilov.delivery.shared.validators.domain.name

import ru.nikpanfilov.delivery.shared.validators.domain.ValidationState

data class NameValidationItem(
	val data: String = "",
	val namePart: NamePart,
	val validationState: ValidationState = ValidationState.Unverified,
)

fun NameValidationItem.isValid(): Boolean = this.validationState is ValidationState.Valid ||
	(namePart == NamePart.PATRONYMIC && this.validationState is ValidationState.Unverified)
