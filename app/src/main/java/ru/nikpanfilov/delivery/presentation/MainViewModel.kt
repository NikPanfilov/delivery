package ru.nikpanfilov.delivery.presentation

import androidx.lifecycle.SavedStateHandle
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import ru.nikpanfilov.delivery.core.presentation.BaseViewModel
import ru.nikpanfilov.delivery.core.token.domain.usecase.IsTokenExistUseCase

class MainViewModel @AssistedInject constructor(
	private val isTokenExistUseCase: IsTokenExistUseCase,
	private val router: MainRouter,
	@Assisted savedStateHandle: SavedStateHandle
) : BaseViewModel<MainState, MainIntent>() {

	override fun initState(): MainState = MainState(currentDestination = null)

	override fun applyIntent(intent: MainIntent) {

	}

	@AssistedFactory
	interface Factory {

		fun create(savedStateHandle: SavedStateHandle): MainViewModel
	}
}