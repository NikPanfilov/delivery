package ru.nikpanfilov.delivery.presentation

import ru.nikpanfilov.delivery.core.navigation.Destination
import ru.nikpanfilov.delivery.core.presentation.State

data class MainState(
	val currentDestination: Destination?,
) : State
