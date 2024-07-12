package ru.nikpanfilov.delivery.feature.addressinfo.presentation

import androidx.lifecycle.SavedStateHandle
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import ru.nikpanfilov.delivery.core.presentation.BaseViewModel
import ru.nikpanfilov.delivery.shared.deliveryinfo.domain.entity.Address
import ru.nikpanfilov.delivery.shared.deliveryinfo.domain.usecase.address.LoadReceiverAddressUseCase
import ru.nikpanfilov.delivery.shared.deliveryinfo.domain.usecase.address.LoadSenderAddressUseCase
import ru.nikpanfilov.delivery.shared.deliveryinfo.domain.usecase.address.SaveReceiverAddressUseCase
import ru.nikpanfilov.delivery.shared.deliveryinfo.domain.usecase.address.SaveSenderAddressUseCase
import ru.nikpanfilov.delivery.shared.validators.domain.DefaultValidationItem
import ru.nikpanfilov.delivery.shared.validators.domain.address.AddressPart
import ru.nikpanfilov.delivery.shared.validators.domain.address.AddressValidationItem
import ru.nikpanfilov.delivery.shared.validators.domain.address.ValidateAddressUseCase
import ru.nikpanfilov.delivery.shared.validators.domain.address.isValid
import ru.nikpanfilov.delivery.shared.validators.domain.courier.ValidateCourierUseCase
import ru.nikpanfilov.delivery.shared.validators.domain.isValid

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
		when (intent) {
			is AddressInfoIntent.LoadData          -> handleLoadData()
			is AddressInfoIntent.ChangeStreet      -> handleChangeStreet(intent.street)
			is AddressInfoIntent.ChangeHouse       -> handleChangeHouse(intent.house)
			is AddressInfoIntent.ChangeApartment   -> handleChangeApartment(intent.apartment)
			is AddressInfoIntent.ChangeCourierNote -> handleChangeCourierNote(intent.courierNote)
			is AddressInfoIntent.Next              -> handleNext()
			is AddressInfoIntent.NavigateBack      -> handleNavigateBack()
		}
	}

	private fun handleLoadData() {
		val address = if (uiState.value.page == InfoPage.SENDER_ADDRESS) {
			loadSenderAddressUseCase()
		} else {
			loadReceiverAddressUseCase()
		}

		address?.let {
			setState(
				uiState.value.copy(
					street = validateAddressUseCase(it.street, AddressPart.STREET),
					house = validateAddressUseCase(it.house, AddressPart.HOUSE),
					apartment = validateAddressUseCase(it.apartment, AddressPart.APARTMENT),
					courierNote = validateCourierUseCase(it.courierNote),
				)
			)
			checkDataValid()
		}
	}

	private fun handleChangeStreet(street: String) {
		setState(
			uiState.value.copy(
				street = validateAddressUseCase(street, AddressPart.STREET),
			)
		)
		checkDataValid()
	}

	private fun checkDataValid() {
		with(uiState.value) {
			setState(
				copy(
					dataValid = street.isValid() && house.isValid() && apartment.isValid() && courierNote.isValid()
				)
			)
		}
	}

	private fun handleChangeHouse(house: String) {
		setState(
			uiState.value.copy(
				house = validateAddressUseCase(house, AddressPart.HOUSE),
			)
		)
		checkDataValid()
	}

	private fun handleChangeApartment(apartment: String) {
		setState(
			uiState.value.copy(
				apartment = validateAddressUseCase(apartment, AddressPart.APARTMENT),
			)
		)
		checkDataValid()
	}

	private fun handleChangeCourierNote(courierNote: String) {
		setState(
			uiState.value.copy(
				courierNote = validateCourierUseCase(courierNote),
			)
		)
		checkDataValid()
	}

	private fun handleNext() {
		with(uiState.value) {
			val address = Address(
				street = street.data,
				house = house.data,
				apartment = apartment.data,
				courierNote = courierNote.data,
			)

			if (page == InfoPage.SENDER_ADDRESS) {
				saveSenderAddressUseCase(address)
				setState(initState().copy(page = InfoPage.RECEIVER_ADDRESS))
				handleLoadData()
			} else {
				saveReceiverAddressUseCase(address)
				router.navigateToShipPayment()
			}
		}
	}

	private fun handleNavigateBack() {
		if (uiState.value.page == InfoPage.SENDER_ADDRESS) {
			router.navigateBack()
		} else {
			setState(uiState.value.copy(page = InfoPage.SENDER_ADDRESS))
			handleLoadData()
		}
	}

	@AssistedFactory
	interface Factory {

		fun create(savedStateHandle: SavedStateHandle): AddressInfoViewModel
	}
}