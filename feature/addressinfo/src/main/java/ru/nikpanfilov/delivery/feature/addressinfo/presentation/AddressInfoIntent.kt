package ru.nikpanfilov.delivery.feature.addressinfo.presentation

import ru.nikpanfilov.delivery.core.presentation.Intent

sealed interface AddressInfoIntent : Intent {

	data object LoadData : AddressInfoIntent

	data object NavigateBack : AddressInfoIntent

	data class ChangeStreet(val street: String) : AddressInfoIntent
	data class ChangeHouse(val house: String) : AddressInfoIntent
	data class ChangeApartment(val apartment: String) : AddressInfoIntent
	data class ChangeCourierNote(val courierNote: String) : AddressInfoIntent

	data object Next : AddressInfoIntent
}