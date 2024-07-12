package ru.nikpanfilov.delivery.feature.addressinfo.presentation

import androidx.lifecycle.SavedStateHandle
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import ru.nikpanfilov.delivery.core.presentation.BaseViewModel
import ru.nikpanfilov.delivery.shared.deliveryinfo.domain.usecase.address.LoadReceiverAddressUseCase
import ru.nikpanfilov.delivery.shared.deliveryinfo.domain.usecase.address.LoadSenderAddressUseCase
import ru.nikpanfilov.delivery.shared.deliveryinfo.domain.usecase.address.SaveReceiverAddressUseCase
import ru.nikpanfilov.delivery.shared.deliveryinfo.domain.usecase.address.SaveSenderAddressUseCase
import ru.nikpanfilov.delivery.shared.validators.domain.DefaultValidationItem
import ru.nikpanfilov.delivery.shared.validators.domain.address.AddressPart
import ru.nikpanfilov.delivery.shared.validators.domain.address.AddressValidationItem
import ru.nikpanfilov.delivery.shared.validators.domain.address.ValidateAddressUseCase
import ru.nikpanfilov.delivery.shared.validators.domain.courier.ValidateCourierUseCase

class AddressInfoViewModel @AssistedInject constructor(
	private val validateAddressUseCase: ValidateAddressUseCase,
	private val validateCourierUseCase: ValidateCourierUseCase,
	private val saveSenderAddressUseCase: SaveSenderAddressUseCase,
	private val saveReceiverAddressUseCase: SaveReceiverAddressUseCase,
	private val loadSenderAddressUseCase: LoadSenderAddressUseCase,
	private val loadReceiverAddressUseCase: LoadReceiverAddressUseCase,
	private val router: AddressInfoRouter,
	@Assisted savedStateHandle: SavedStateHandle,
) : BaseViewModel<AddressInfoState, AddressInfoIntent>() {

	override fun initState(): AddressInfoState = AddressInfoState(
		page = InfoPage.SENDER_ADDRESS,
		street = AddressValidationItem(addressPart = AddressPart.STREET),
		house = AddressValidationItem(addressPart = AddressPart.HOUSE),
		apartment = AddressValidationItem(addressPart = AddressPart.APARTMENT),
		courierNote = DefaultValidationItem(optional = true),
		dataValid = false,
	)

	override fun applyIntent(intent: AddressInfoIntent) {
	}

	@AssistedFactory
	interface Factory {

		fun create(savedStateHandle: SavedStateHandle): AddressInfoViewModel
	}
}