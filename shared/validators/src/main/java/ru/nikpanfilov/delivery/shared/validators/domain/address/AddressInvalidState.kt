package ru.nikpanfilov.delivery.shared.validators.domain.address

import ru.nikpanfilov.delivery.shared.validators.domain.InvalidState

enum class AddressInvalidState : InvalidState {
	EMPTY,
	DIFFERENT_ALPHABETS,
	INCORRECT_FORMAT,
	TOO_LONG,
}