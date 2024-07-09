package ru.nikpanfilov.delivery.feature.signin.presentation

import ru.nikpanfilov.delivery.core.presentation.State
import ru.nikpanfilov.delivery.shared.validators.domain.DefaultValidationItem

data class SignInState(
	val phone: DefaultValidationItem,
	val codeStatus: CodeStatus,
	val loadingStatus: LoadingStatus,
	val dataValid: Boolean,
) : State

sealed interface CodeStatus {

	data object NotSent : CodeStatus

	data class Sent(
		val code: DefaultValidationItem,
		val retryDelay: Double?,
	) : CodeStatus
}

sealed interface LoadingStatus {

	data object None : LoadingStatus

	data object Loading : LoadingStatus

	data object Error : LoadingStatus
}
