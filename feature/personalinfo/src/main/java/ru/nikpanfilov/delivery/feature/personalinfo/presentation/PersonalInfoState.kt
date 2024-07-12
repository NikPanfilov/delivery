package ru.nikpanfilov.delivery.feature.personalinfo.presentation

import ru.nikpanfilov.delivery.core.presentation.State
import ru.nikpanfilov.delivery.shared.validators.domain.DefaultValidationItem
import ru.nikpanfilov.delivery.shared.validators.domain.name.NameValidationItem

sealed interface PersonalInfoState : State {

	data object Initial : PersonalInfoState
	data object Loading : PersonalInfoState
	data object Error : PersonalInfoState

	data class Content(
		val page: InfoPage,
		val firstname: NameValidationItem,
		val middlename: NameValidationItem,
		val lastname: NameValidationItem,
		val phone: DefaultValidationItem,
		val dataValid: Boolean,
	) : PersonalInfoState
}

enum class InfoPage {
	SENDER_INFO,
	RECEIVER_INFO,
}