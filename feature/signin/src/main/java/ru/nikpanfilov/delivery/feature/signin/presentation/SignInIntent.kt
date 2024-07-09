package ru.nikpanfilov.delivery.feature.signin.presentation

import ru.nikpanfilov.delivery.core.presentation.Intent

sealed interface SignInIntent : Intent {

	data object NavigateBack : SignInIntent

	data class ChangePhone(val phone: String) : SignInIntent
	data object ResetPhone : SignInIntent
	data class ChangeCode(val code: String) : SignInIntent

	data object Next : SignInIntent
	data object ResendCode : SignInIntent
}