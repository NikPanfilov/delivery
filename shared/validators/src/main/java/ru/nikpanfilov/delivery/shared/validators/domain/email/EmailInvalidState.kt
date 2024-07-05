package ru.nikpanfilov.delivery.shared.validators.domain.email

import ru.nikpanfilov.delivery.shared.validators.domain.InvalidState

enum class EmailInvalidState : InvalidState {
	EMPTY,
	INCORRECT_FORMAT,
}