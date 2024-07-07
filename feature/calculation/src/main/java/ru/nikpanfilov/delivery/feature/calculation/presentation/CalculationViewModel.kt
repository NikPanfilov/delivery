package ru.nikpanfilov.delivery.feature.calculation.presentation

import androidx.lifecycle.SavedStateHandle
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import ru.nikpanfilov.delivery.core.error.presentation.ErrorDelegate
import ru.nikpanfilov.delivery.core.error.presentation.asCoroutineExceptionHandler
import ru.nikpanfilov.delivery.core.presentation.BaseViewModel
import ru.nikpanfilov.delivery.feature.calculation.domain.usecase.CalculateDeliveryUseCase
import ru.nikpanfilov.delivery.feature.calculation.domain.usecase.GetPackageTypesUseCase
import ru.nikpanfilov.delivery.shared.cities.domain.usecase.GetDeliveryCitiesUseCase

class CalculationViewModel @AssistedInject constructor(
	private val getDeliveryCitiesUseCase: GetDeliveryCitiesUseCase,
	private val getPackageTypesUseCase: GetPackageTypesUseCase,
	private val calculateDeliveryUseCase: CalculateDeliveryUseCase,
	private val router: CalculationRouter,
	errorDelegate: ErrorDelegate,
	@Assisted savedStateHandle: SavedStateHandle,
) : BaseViewModel<CalculationState, CalculationIntent>() {

	override fun initState(): CalculationState = CalculationState.Initial

	private val errorHandler = errorDelegate.asCoroutineExceptionHandler {

	}

	override fun applyIntent(intent: CalculationIntent) {
	}

	@AssistedFactory
	interface Factory {

		fun create(savedStateHandle: SavedStateHandle): CalculationViewModel
	}
}