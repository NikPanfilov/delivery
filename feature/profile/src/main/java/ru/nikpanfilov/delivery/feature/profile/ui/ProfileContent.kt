package ru.nikpanfilov.delivery.feature.profile.ui

import android.view.MotionEvent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.nikpanfilov.delivery.core.ui.compose.BodyLargeText
import ru.nikpanfilov.delivery.core.ui.compose.BodyMediumText
import ru.nikpanfilov.delivery.core.ui.compose.CustomButton
import ru.nikpanfilov.delivery.core.ui.compose.TextInputField
import ru.nikpanfilov.delivery.feature.profile.R
import ru.nikpanfilov.delivery.feature.profile.presentation.ProfileIntent
import ru.nikpanfilov.delivery.feature.profile.presentation.ProfileState
import ru.nikpanfilov.delivery.shared.cities.domain.entity.DeliveryPoint
import ru.nikpanfilov.delivery.shared.validators.ui.getEmailError
import ru.nikpanfilov.delivery.shared.validators.ui.getNameError

@OptIn(ExperimentalComposeUiApi::class)
@Composable
internal fun ProfileContent(
	state: ProfileState.Content,
	applyIntent: (ProfileIntent) -> Unit,
) {
	with(state) {
		val scrollState = rememberScrollState()
		Column(
			modifier = Modifier
				.padding(vertical = 24.dp, horizontal = 16.dp)
				.verticalScroll(scrollState),
		) {
			TextInputField(
				text = lastname.data,
				label = stringResource(R.string.lastname_label),
				onValueChanged = { applyIntent(ProfileIntent.ChangeLastname(it)) },
				errorMessage = getNameError(lastname.validationState) ?: "",
			)

			Spacer(modifier = Modifier.height(24.dp))

			TextInputField(
				text = firstname.data,
				label = stringResource(R.string.firstname_label),
				onValueChanged = { applyIntent(ProfileIntent.ChangeFirstname(it)) },
				errorMessage = getNameError(firstname.validationState) ?: "",
			)

			Spacer(modifier = Modifier.height(24.dp))

			TextInputField(
				text = middlename.data,
				label = stringResource(R.string.middlename_label),
				onValueChanged = { applyIntent(ProfileIntent.ChangeMiddlename(it)) },
				errorMessage = getNameError(middlename.validationState) ?: "",
				optional = true,
			)

			Spacer(modifier = Modifier.height(24.dp))

			TextInputField(
				text = city?.name ?: "",
				label = stringResource(R.string.city_label),
				onValueChanged = { },
				readOnly = true,
				modifier = Modifier.pointerInteropFilter {
					(it.action == MotionEvent.ACTION_DOWN).also {
						if (it) {
							applyIntent(ProfileIntent.ChangeCity)
						}
					}
				}
			)

			Spacer(modifier = Modifier.height(24.dp))

			TextInputField(
				text = phone,
				label = stringResource(R.string.phone_label),
				onValueChanged = { },
				enabled = false,
			)

			Spacer(modifier = Modifier.height(24.dp))

			TextInputField(
				text = email.data,
				label = stringResource(R.string.email_label),
				onValueChanged = { applyIntent(ProfileIntent.ChangeEmail(it)) },
				errorMessage = getEmailError(email.validationState) ?: "",
				optional = true,
			)

			Spacer(modifier = Modifier.height(24.dp))

			CustomButton(
				text = stringResource(R.string.save_data),
				onClick = { applyIntent(ProfileIntent.SaveData) },
				enabled = dataValid,
			)
		}

		if (changingCity) {
			CitiesBottomsheet(
				options = cityOptions,
				onSetCity = { applyIntent(ProfileIntent.SetCity(it)) },
				onDismiss = { applyIntent(ProfileIntent.ChangeCity) },
			)
		}
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CitiesBottomsheet(
	options: List<DeliveryPoint>,
	onSetCity: (DeliveryPoint) -> Unit,
	onDismiss: () -> Unit,
) {
	ModalBottomSheet(
		onDismissRequest = onDismiss,
		dragHandle = {},
	) {
		Column(modifier = Modifier.padding(start = 16.dp, top = 20.dp)) {
			BodyLargeText(
				text = stringResource(R.string.city_label),
				modifier = Modifier.padding(vertical = 16.dp),
			)

			LazyColumn {
				items(
					items = options,
					key = DeliveryPoint::id,
				) {
					TextButton(
						onClick = { onSetCity(it) },
						modifier = Modifier.fillMaxWidth(),
						shape = RectangleShape,
					) {
						BodyMediumText(text = it.name)
					}
				}
			}
		}
	}
}