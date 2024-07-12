package ru.nikpanfilov.delivery.feature.addressinfo.presentation

import ru.nikpanfilov.delivery.core.presentation.State
import ru.nikpanfilov.delivery.shared.validators.domain.DefaultValidationItem
import ru.nikpanfilov.delivery.shared.validators.domain.address.AddressValidationItem

data class AddressInfoState(
	val page: InfoPage,
	val street: AddressValidationItem,
	val house: AddressValidationItem,
	val apartment: AddressValidationItem,
	val courierNote: DefaultValidationItem,
	val dataValid: Boolean,
) : State

enum class InfoPage {
	SENDER_ADDRESS,
	RECEIVER_ADDRESS,
}
