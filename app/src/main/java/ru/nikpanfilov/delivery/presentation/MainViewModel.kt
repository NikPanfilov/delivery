package ru.nikpanfilov.delivery.presentation

import androidx.lifecycle.SavedStateHandle
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import ru.nikpanfilov.delivery.core.navigation.Destination
import ru.nikpanfilov.delivery.core.presentation.BaseViewModel
import ru.nikpanfilov.delivery.core.token.domain.usecase.IsTokenExistUseCase

class MainViewModel @AssistedInject constructor(
	private val isTokenExistUseCase: IsTokenExistUseCase,
	private val router: MainRouter,
	@Assisted savedStateHandle: SavedStateHandle
) : BaseViewModel<MainState, MainIntent>() {

	override fun initState(): MainState = MainState(currentDestination = null)

	override fun applyIntent(intent: MainIntent) {
		when (intent) {
			is MainIntent.OpenCalculation -> handleOpenCalculation()
			is MainIntent.OpenHistory     -> handleOpenHistory()
			is MainIntent.OpenProfile     -> handleOpenProfile()
			is MainIntent.SetOpenedScreen -> handleSetOpenedScreen(intent.destination)
		}
	}

	private fun handleOpenCalculation() {
		router.navigateToCalculation()
	}

	private fun handleOpenHistory() {
		router.navigateToHistory()
	}

	private fun handleOpenProfile() {
		if (isTokenExistUseCase()) {
			router.navigateToProfile()
		} else {
			router.navigateToSignIn()
		}
	}

	private fun handleSetOpenedScreen(destination: Destination) {
		setState(MainState(destination))
	}

	@AssistedFactory
	interface Factory {

		fun create(savedStateHandle: SavedStateHandle): MainViewModel
	}
}