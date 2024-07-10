package ru.nikpanfilov.delivery.feature.profile.presentation

import ru.nikpanfilov.delivery.core.presentation.State
import ru.nikpanfilov.delivery.shared.cities.domain.entity.DeliveryPoint
import ru.nikpanfilov.delivery.shared.validators.domain.DefaultValidationItem
import ru.nikpanfilov.delivery.shared.validators.domain.name.NameValidationItem

sealed interface ProfileState : State {

	data object Initial : ProfileState
	data object Loading : ProfileState
	data object Error : ProfileState

	data class Content(
		val firstname: NameValidationItem,
		val middlename: NameValidationItem,
		val lastname: NameValidationItem,
		val city: DeliveryPoint?,
		val changingCity: Boolean,
		val cityOptions: List<DeliveryPoint>,
		val phone: String,
		val email: DefaultValidationItem,
		val dataValid: Boolean,
	) : ProfileState
}