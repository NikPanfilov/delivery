package ru.nikpanfilov.delivery.shared.validators.domain.code

import ru.nikpanfilov.delivery.shared.validators.domain.InvalidState

enum class CodeInvalidState : InvalidState {
	EMPTY,
	RESTRICTED_SYMBOLS,
	WRONG_LENGTH,
}