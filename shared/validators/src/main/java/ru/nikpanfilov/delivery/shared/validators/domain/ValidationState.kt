package ru.nikpanfilov.delivery.shared.validators.domain

interface InvalidState

sealed interface ValidationState {

	data object Unverified : ValidationState

	data object Valid : ValidationState

	data class Invalid(val invalidState: InvalidState) : ValidationState
}