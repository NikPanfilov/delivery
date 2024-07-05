package ru.nikpanfilov.delivery.presentation

import ru.nikpanfilov.delivery.core.navigation.Destination
import ru.nikpanfilov.delivery.core.presentation.Intent

sealed interface MainIntent : Intent {

	data object OpenCalculation : MainIntent
	data object OpenHistory : MainIntent
	data object OpenProfile : MainIntent

	data class SetOpenedScreen(
		val destination: Destination,
	) : MainIntent
}