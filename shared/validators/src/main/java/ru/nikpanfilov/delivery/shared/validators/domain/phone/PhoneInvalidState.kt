package ru.nikpanfilov.delivery.shared.validators.domain.phone

import ru.nikpanfilov.delivery.shared.validators.domain.InvalidState

enum class PhoneInvalidState : InvalidState {
	EMPTY,
	RESTRICTED_SYMBOLS,
}