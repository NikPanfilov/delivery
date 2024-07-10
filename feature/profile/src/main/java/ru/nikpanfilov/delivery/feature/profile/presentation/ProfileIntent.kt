package ru.nikpanfilov.delivery.feature.profile.presentation

import ru.nikpanfilov.delivery.core.presentation.Intent
import ru.nikpanfilov.delivery.shared.cities.domain.entity.DeliveryPoint

sealed interface ProfileIntent : Intent {

	data object NavigateBack : ProfileIntent

	data class ChangeFirstname(val name: String) : ProfileIntent
	data class ChangeLastname(val name: String) : ProfileIntent
	data class ChangeMiddlename(val name: String) : ProfileIntent
	data class ChangeEmail(val email: String) : ProfileIntent

	data object ChangeCity : ProfileIntent
	data class SetCity(val city: DeliveryPoint) : ProfileIntent

	data object LoadData : ProfileIntent
	data object SaveData : ProfileIntent
}