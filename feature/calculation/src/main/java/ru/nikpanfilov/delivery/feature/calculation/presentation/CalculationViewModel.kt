package ru.nikpanfilov.delivery.feature.calculation.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch
import ru.nikpanfilov.delivery.core.error.presentation.ErrorDelegate
import ru.nikpanfilov.delivery.core.error.presentation.asCoroutineExceptionHandler
import ru.nikpanfilov.delivery.core.presentation.BaseViewModel
import ru.nikpanfilov.delivery.feature.calculation.domain.entity.CalculateDelivery
import ru.nikpanfilov.delivery.feature.calculation.domain.entity.PackageType
import ru.nikpanfilov.delivery.feature.calculation.domain.usecase.CalculateDeliveryUseCase
import ru.nikpanfilov.delivery.feature.calculation.domain.usecase.GetPackageTypesUseCase
import ru.nikpanfilov.delivery.shared.cities.domain.entity.DeliveryPoint
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
		setState(CalculationState.Error)
	}

	override fun applyIntent(intent: CalculationIntent) {
		when (intent) {
			is CalculationIntent.LoadData            -> handleLoadData()
			is CalculationIntent.SetSenderCity       -> handleSetSenderCity(intent.city)
			is CalculationIntent.SetReceiverCity     -> handleSetReceiverCity(intent.city)
			is CalculationIntent.SetPackageType      -> handleSetPackageType(intent.packageType)
			is CalculationIntent.ChangeSenderCity    -> handleChangeSenderCity()
			is CalculationIntent.ChangeReceiverCity  -> handleChangeReceiverCity()
			is CalculationIntent.ChangePackageType   -> handleChangePackageType()
			is CalculationIntent.CancelChangeDetails -> handleCancelChangeDetails()
			is CalculationIntent.Calculate           -> handleCalculate()
		}
	}

	private fun handleLoadData() {
		setState(CalculationState.Loading)

		viewModelScope.launch(errorHandler) {
			val deliveryPoints = getDeliveryCitiesUseCase()
			val packageTypes = getPackageTypesUseCase()

			setState(
				CalculationState.Content(
					sendPoint = deliveryPoints.first(),
					receiverPoint = deliveryPoints.first(),
					deliveryPointOptions = deliveryPoints,
					packageType = packageTypes.first(),
					packageTypeOptions = packageTypes,
					detailsChange = null,
				)
			)
		}
	}

	private fun handleSetSenderCity(city: DeliveryPoint) {
		val state = uiState.value as? CalculationState.Content ?: return
		setState(
			state.copy(
				sendPoint = city,
				detailsChange = null,
			)
		)
	}

	private fun handleSetReceiverCity(city: DeliveryPoint) {
		val state = uiState.value as? CalculationState.Content ?: return
		setState(
			state.copy(
				receiverPoint = city,
				detailsChange = null,
			)
		)
	}

	private fun handleSetPackageType(packageType: PackageType) {
		val state = uiState.value as? CalculationState.Content ?: return
		setState(
			state.copy(
				packageType = packageType,
				detailsChange = null,
			)
		)
	}

	private fun handleChangeSenderCity() {
		val state = uiState.value as? CalculationState.Content ?: return
		setState(
			state.copy(
				detailsChange = DetailsChange.SEND_POINT,
			)
		)
	}

	private fun handleChangeReceiverCity() {
		val state = uiState.value as? CalculationState.Content ?: return
		setState(
			state.copy(
				detailsChange = DetailsChange.RECEIVER_POINT,
			)
		)
	}

	private fun handleChangePackageType() {
		val state = uiState.value as? CalculationState.Content ?: return
		setState(
			state.copy(
				detailsChange = DetailsChange.PACKAGE_TYPE,
			)
		)
	}

	private fun handleCancelChangeDetails() {
		val state = uiState.value as? CalculationState.Content ?: return
		setState(
			state.copy(
				detailsChange = null,
			)
		)
	}

	private fun handleCalculate() {
		val state = uiState.value as? CalculationState.Content ?: return
		setState(CalculationState.Loading)

		viewModelScope.launch(errorHandler) {
			val deliveryOptions = calculateDeliveryUseCase(
				CalculateDelivery(
					packageType = state.packageType,
					senderPoint = state.sendPoint,
					receiverPoint = state.receiverPoint,
				)
			)

			setState(state)
			router.navigateToDeliveryOptions(deliveryOptions)
		}
	}

	@AssistedFactory
	interface Factory {

		fun create(savedStateHandle: SavedStateHandle): CalculationViewModel
	}
}