package ru.nikpanfilov.delivery.shared.validators.domain

data class DefaultValidationItem(
	val data: String = "",
	val optional: Boolean = false,
	val validationState: ValidationState = ValidationState.Unverified,
)

fun DefaultValidationItem.isValid(): Boolean = this.validationState is ValidationState.Valid ||
	(this.optional && this.validationState is ValidationState.Unverified)
