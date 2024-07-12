package ru.nikpanfilov.delivery.feature.personalinfo.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import ru.nikpanfilov.delivery.feature.personalinfo.presentation.PersonalInfoIntent
import ru.nikpanfilov.delivery.feature.personalinfo.presentation.PersonalInfoState
import ru.nikpanfilov.delivery.feature.senderinfo.R
import ru.nikpanfilov.delivery.shared.validators.ui.getNameError

@Composable
internal fun ProfileInfoContent(
	state: PersonalInfoState.Content,
	applyIntent: (PersonalInfoIntent) -> Unit,
) {
	with(state) {
		val scroll = rememberScrollState()

		Column(
			modifier = Modifier
				.padding(top = 24.dp)
				.padding(horizontal = 16.dp)
				.verticalScroll(scroll),
		) {
			TextInputField(
				text = firstname.data,
				label = stringResource(R.string.firstname_label),
				onValueChanged = { applyIntent(PersonalInfoIntent.ChangeFirstname(it)) },
				errorMessage = getNameError(state = firstname.validationState) ?: "",
			)

			Spacer(modifier = Modifier.height(24.dp))

			TextInputField(
				text = middlename.data,
				label = stringResource(R.string.middlename_label),
				onValueChanged = { applyIntent(PersonalInfoIntent.ChangeMiddlename(it)) },
				errorMessage = getNameError(state = middlename.validationState) ?: "",
			)

			Spacer(modifier = Modifier.height(24.dp))

			TextInputField(
				text = lastname.data,
				label = stringResource(R.string.lastname_label),
				onValueChanged = { applyIntent(PersonalInfoIntent.ChangeLastname(it)) },
				errorMessage = getNameError(state = lastname.validationState) ?: "",
			)

			Spacer(modifier = Modifier.height(24.dp))

			TextInputField(
				text = phone.data,
				label = stringResource(R.string.phone_label),
				onValueChanged = { applyIntent(PersonalInfoIntent.ChangePhone(it)) },
				errorMessage = getNameError(state = phone.validationState) ?: "",
			)

			Spacer(modifier = Modifier.height(40.dp))

			CustomButton(
				text = stringResource(R.string.continue_text),
				onClick = { applyIntent(PersonalInfoIntent.Next) },
				enabled = dataValid,
			)
		}
	}
}