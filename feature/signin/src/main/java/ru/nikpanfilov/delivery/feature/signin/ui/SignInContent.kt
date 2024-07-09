package ru.nikpanfilov.delivery.feature.signin.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import ru.nikpanfilov.delivery.core.ui.compose.BaseText
import ru.nikpanfilov.delivery.core.ui.compose.BodyLargeText
import ru.nikpanfilov.delivery.core.ui.compose.BodyMediumText
import ru.nikpanfilov.delivery.core.ui.compose.CustomButton
import ru.nikpanfilov.delivery.core.ui.compose.TextInputField
import ru.nikpanfilov.delivery.feature.signin.R
import ru.nikpanfilov.delivery.feature.signin.presentation.CodeStatus
import ru.nikpanfilov.delivery.feature.signin.presentation.SignInIntent
import ru.nikpanfilov.delivery.feature.signin.presentation.SignInState
import ru.nikpanfilov.delivery.shared.validators.domain.DefaultValidationItem
import ru.nikpanfilov.delivery.shared.validators.ui.getCodeError
import ru.nikpanfilov.delivery.shared.validators.ui.getPhoneError
import kotlin.math.roundToInt

@Composable
internal fun SignInContent(
	state: SignInState,
	applyIntent: (SignInIntent) -> Unit,
) {
	with(state) {
		Column(
			modifier = Modifier
				.padding(horizontal = 16.dp)
				.padding(top = 24.dp)
		) {
			BodyLargeText(text = stringResource(R.string.helper_text))

			Spacer(modifier = Modifier.height(24.dp))

			TextInputField(
				text = phone.data,
				label = stringResource(id = R.string.phone),
				onValueChanged = {
					applyIntent(SignInIntent.ChangePhone(it))
				},
				enabled = codeStatus is CodeStatus.NotSent,
				errorMessage = getPhoneError(phone.validationState) ?: "",
			)

			if (codeStatus is CodeStatus.Sent) {
				CodeBlock(
					code = codeStatus.code,
					onResetPhone = { applyIntent(SignInIntent.ResetPhone) },
					onChangeCode = { applyIntent(SignInIntent.ChangeCode(it)) },
				)
			}

			Spacer(modifier = Modifier.height(40.dp))

			CustomButton(
				text = stringResource(R.string.enter),
				onClick = { applyIntent(SignInIntent.Next) },
				enabled = dataValid,
			)

			if (codeStatus is CodeStatus.Sent) {
				ResendBlock(
					retryDelay = codeStatus.retryDelay,
					onResendCode = { applyIntent(SignInIntent.ResendCode) }
				)
			}
		}
	}
}

@Composable
private fun ColumnScope.CodeBlock(
	code: DefaultValidationItem,
	onResetPhone: () -> Unit,
	onChangeCode: (String) -> Unit,
) {
	Spacer(modifier = Modifier.height(8.dp))

	BodyMediumText(
		text = stringResource(R.string.change_phone),
		modifier = Modifier
			.align(Alignment.End)
			.clickable {
				onResetPhone()
			},
	)

	Spacer(modifier = Modifier.height(24.dp))

	TextInputField(
		text = code.data,
		label = stringResource(id = R.string.code),
		onValueChanged = onChangeCode,
		errorMessage = getCodeError(code.validationState) ?: "",
	)
}

@Composable
private fun ColumnScope.ResendBlock(
	retryDelay: Double?,
	onResendCode: () -> Unit,
) {
	Spacer(modifier = Modifier.height(40.dp))

	if (retryDelay == null) {
		BaseText(
			text = stringResource(R.string.resend_code),
			style = MaterialTheme.typography.bodyMedium.copy(
				textDecoration = TextDecoration.Underline,
			),
			modifier = Modifier
				.align(Alignment.CenterHorizontally)
				.clickable { onResendCode() },
		)
	} else {
		BodyMediumText(
			text = stringResource(R.string.resend_code_after, retryDelay.millsToSeconds()),
			modifier = Modifier.align(Alignment.CenterHorizontally),
		)
	}
}

private fun Double.millsToSeconds(): Int = (this / 1000).roundToInt()