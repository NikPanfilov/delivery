package ru.nikpanfilov.delivery.shared.validators.domain.empty

import ru.nikpanfilov.delivery.shared.validators.domain.DefaultValidationItem
import ru.nikpanfilov.delivery.shared.validators.domain.ValidationState
import javax.inject.Inject

/*
Подходит для:
Размера посылки

 */
class ValidateNotEmptyUseCase @Inject constructor() {

	operator fun invoke(data: String): DefaultValidationItem = DefaultValidationItem(
		data = data,
		validationState = if (data.isEmpty()) {
			ValidationState.Invalid(EmptyInvalidState)
		} else {
			ValidationState.Valid
		}
	)
}