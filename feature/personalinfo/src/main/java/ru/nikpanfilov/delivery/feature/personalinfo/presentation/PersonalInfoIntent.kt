package ru.nikpanfilov.delivery.feature.personalinfo.presentation

import ru.nikpanfilov.delivery.core.presentation.Intent

sealed interface PersonalInfoIntent : Intent {

	data object LoadData : PersonalInfoIntent

	data object NavigateBack : PersonalInfoIntent

	data class ChangeFirstname(val name: String) : PersonalInfoIntent
	data class ChangeMiddlename(val name: String) : PersonalInfoIntent
	data class ChangeLastname(val name: String) : PersonalInfoIntent
	data class ChangePhone(val phone: String) : PersonalInfoIntent

	data object Next : PersonalInfoIntent
}