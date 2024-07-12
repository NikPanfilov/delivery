package ru.nikpanfilov.delivery.feature.addressinfo.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.nikpanfilov.delivery.core.ui.compose.CustomButton
import ru.nikpanfilov.delivery.core.ui.compose.TextInputField
import ru.nikpanfilov.delivery.feature.addressinfo.presentation.AddressInfoIntent
import ru.nikpanfilov.delivery.feature.addressinfo.presentation.AddressInfoState
import ru.nikpanfilov.delivery.feature.deliveryinfo.R
import ru.nikpanfilov.delivery.shared.validators.ui.getAddressError

@Composable
internal fun AddressInfoContent(
	state: AddressInfoState,
	applyIntent: (AddressInfoIntent) -> Unit,
) {
	with(state) {
		val scroll = rememberScrollState()

		Column(
			modifier = Modifier
				.fillMaxSize()
				.padding(top = 24.dp)
				.padding(horizontal = 16.dp)
				.verticalScroll(scroll)
		) {
			TextInputField(
				text = street.data,
				label = stringResource(R.string.street),
				onValueChanged = { applyIntent(AddressInfoIntent.ChangeStreet(it)) },
				errorMessage = getAddressError(street.validationState) ?: "",
			)

			Spacer(modifier = Modifier.height(24.dp))

			TextInputField(
				text = house.data,
				label = stringResource(R.string.house),
				onValueChanged = { applyIntent(AddressInfoIntent.ChangeHouse(it)) },
				errorMessage = getAddressError(house.validationState) ?: "",
			)

			Spacer(modifier = Modifier.height(24.dp))

			TextInputField(
				text = apartment.data,
				label = stringResource(R.string.apartment),
				onValueChanged = { applyIntent(AddressInfoIntent.ChangeApartment(it)) },
				errorMessage = getAddressError(apartment.validationState) ?: "",
				optional = true,
			)

			Spacer(modifier = Modifier.height(24.dp))

			TextInputField(
				text = courierNote.data,
				label = stringResource(R.string.courier_note),
				onValueChanged = { applyIntent(AddressInfoIntent.ChangeCourierNote(it)) },
				errorMessage = getAddressError(courierNote.validationState) ?: "",
				optional = true,
			)

			Spacer(modifier = Modifier.height(40.dp))

			CustomButton(
				text = stringResource(R.string.next),
				onClick = { applyIntent(AddressInfoIntent.Next) },
			)
		}
	}
}