package ru.nikpanfilov.delivery.shared.validators.domain.name

import ru.nikpanfilov.delivery.shared.validators.domain.InvalidState

enum class NameInvalidState : InvalidState {
	EMPTY,
	DIFFERENT_ALPHABETS,
	INCORRECT_FORMAT,
	TOO_LONG,
}