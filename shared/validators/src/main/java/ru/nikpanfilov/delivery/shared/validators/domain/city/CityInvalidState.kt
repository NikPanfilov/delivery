package ru.nikpanfilov.delivery.shared.validators.domain.city

import ru.nikpanfilov.delivery.shared.validators.domain.InvalidState

enum class CityInvalidState : InvalidState {
	EMPTY,
	DIFFERENT_ALPHABETS,
	INCORRECT_FORMAT,
	TOO_LONG,
}