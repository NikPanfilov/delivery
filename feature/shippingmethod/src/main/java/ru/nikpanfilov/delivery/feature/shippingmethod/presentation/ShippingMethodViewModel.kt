package ru.nikpanfilov.delivery.feature.shippingmethod.presentation

import androidx.lifecycle.SavedStateHandle
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import ru.nikpanfilov.delivery.core.presentation.BaseViewModel
import ru.nikpanfilov.delivery.shared.deliveryinfo.domain.entity.DeliveryOption
import ru.nikpanfilov.delivery.shared.deliveryinfo.domain.usecase.SaveDeliveryOptionUseCase

class ShippingMethodViewModel @AssistedInject constructor(
	private val saveDeliveryOptionUseCase: SaveDeliveryOptionUseCase,
	private val router: ShippingMethodRouter,
	@Assisted val deliveryOptions: List<DeliveryOption>,
	@Assisted savedStateHandle: SavedStateHandle,
) : BaseViewModel<ShippingMethodState, ShippingMethodIntent>() {

	override fun initState(): ShippingMethodState = ShippingMethodState(deliveryOptions)

	override fun applyIntent(intent: ShippingMethodIntent) {
		when (intent) {
			is ShippingMethodIntent.ChooseDeliveryOption -> handleChooseDeliveryOption(intent.option)
			is ShippingMethodIntent.NavigateBack         -> handleNavigateBack()
		}
	}

	private fun handleChooseDeliveryOption(option: DeliveryOption) {
		saveDeliveryOptionUseCase(option)
		router.navigateToSenderInfo()
	}

	private fun handleNavigateBack() {
		router.navigateBack()
	}

	@AssistedFactory
	interface Factory {

		fun create(
			savedStateHandle: SavedStateHandle,
			deliveryOptions: List<DeliveryOption>,
		): ShippingMethodViewModel
	}
}