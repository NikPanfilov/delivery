package ru.nikpanfilov.delivery.core.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.nikpanfilov.delivery.core.ui.R
import ru.nikpanfilov.delivery.core.ui.ext.shimmerAnimation
import ru.nikpanfilov.delivery.core.ui.theme.Typography

/**
 * Компонент в фигме:
 * [Figma](https://www.figma.com/file/jBaZDfTxkix3FZ2iNC0oiQ/%D0%9C%D0%BE%D0%B1%D0%B8%D0%BB%D1%8C%D0%BD%D0%BE%D0%B5-%D0%BF%D1%80%D0%B8%D0%BB%D0%BE%D0%B6%D0%B5%D0%BD%D0%B8%D0%B5-%D0%A8%D0%98%D0%A4%D0%A2-%D0%98%D0%BD%D1%82%D0%B5%D0%BD%D1%81%D0%B8%D0%B2?type=design&node-id=2160-3057&mode=design&t=GOPA8sXlY2WQ4pk3-4)
 */
@Composable
fun TextInputField(
	text: String,
	label: String,
	onValueChanged: (String) -> Unit,
	modifier: Modifier = Modifier,
	errorVisible: Boolean = false,
	errorMessage: String = "",
	supportMessage: String? = null,
	enabled: Boolean = true,
	readOnly: Boolean = false,
	leadingIconId: Int? = null,
	visualTransformation: VisualTransformation = VisualTransformation.None,
	keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
	keyboardActions: KeyboardActions = KeyboardActions.Default,
	singleLine: Boolean = true,
	optional: Boolean = false,
	visibilitySupportingText: Boolean = true,
) {
	var focused by remember { mutableStateOf(text.isNotEmpty()) }
	var labelStyle by remember {
		mutableStateOf(
			selectLabelStyle(focused, text.isNotEmpty())
		)
	}

	LaunchedEffect(text, focused) {
		labelStyle = selectLabelStyle(focused, text.isNotEmpty())
	}

	Column(modifier = modifier.fillMaxWidth()) {
		OutlinedTextFieldBackground(
			enabledColor = MaterialTheme.colorScheme.surface,
			disabledColor = MaterialTheme.colorScheme.surfaceDim,
			enabled = enabled
		) {
			OutlinedTextField(
				value = text,
				onValueChange = onValueChanged,
				textStyle = MaterialTheme.typography.bodyLarge,
				enabled = enabled,
				readOnly = readOnly,
				colors = textInputFieldColors(),
				isError = errorVisible,
				shape = MaterialTheme.shapes.medium,
				leadingIcon = getLeadingIcon(leadingIconId),
				trailingIcon = getErrorIcon(errorVisible),
				visualTransformation = visualTransformation,
				keyboardOptions = keyboardOptions,
				keyboardActions = keyboardActions,
				singleLine = singleLine,
				label = {
					BaseText(text = label, style = labelStyle)
				},
				modifier = Modifier
					.fillMaxWidth()
					.onFocusChanged {
						focused = it.isFocused
					},
			)
		}

		if (visibilitySupportingText) {
			SupportingText(
				errorMessage = errorMessage,
				optional = optional,
				optionalMessage = supportMessage,
			)
		}
	}
}

@Composable
fun TextInputField(
	textFieldValue: TextFieldValue,
	label: String,
	onValueChanged: (TextFieldValue) -> Unit,
	modifier: Modifier = Modifier,
	errorVisible: Boolean = false,
	errorMessage: String = "",
	supportMessage: String = "",
	enabled: Boolean = true,
	readOnly: Boolean = false,
	leadingIconId: Int? = null,
	visualTransformation: VisualTransformation = VisualTransformation.None,
	keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
	keyboardActions: KeyboardActions = KeyboardActions.Default,
	singleLine: Boolean = true,
	optional: Boolean = false,
	visibilitySupportingText: Boolean = true,
) {
	var focused by remember { mutableStateOf(textFieldValue.text.isNotEmpty()) }
	var labelStyle by remember {
		mutableStateOf(
			selectLabelStyle(focused, textFieldValue.text.isNotEmpty())
		)
	}

	LaunchedEffect(textFieldValue, focused) {
		labelStyle = selectLabelStyle(focused, textFieldValue.text.isNotEmpty())
	}

	Column(modifier = modifier.fillMaxWidth()) {
		OutlinedTextFieldBackground(
			enabledColor = MaterialTheme.colorScheme.surface,
			disabledColor = MaterialTheme.colorScheme.surfaceDim,
			enabled = enabled
		) {
			OutlinedTextField(
				value = textFieldValue,
				onValueChange = onValueChanged,
				textStyle = MaterialTheme.typography.bodyLarge,
				enabled = enabled,
				readOnly = readOnly,
				colors = textInputFieldColors(),
				isError = errorVisible,
				shape = MaterialTheme.shapes.medium,
				leadingIcon = getLeadingIcon(leadingIconId),
				trailingIcon = getErrorIcon(errorVisible),
				visualTransformation = visualTransformation,
				keyboardOptions = keyboardOptions,
				keyboardActions = keyboardActions,
				singleLine = singleLine,
				label = {
					BaseText(text = label, style = labelStyle)
				},
				modifier = Modifier
					.fillMaxWidth()
					.onFocusChanged {
						focused = it.isFocused
					},
			)
		}

		if (visibilitySupportingText) {
			SupportingText(
				errorMessage = errorMessage,
				optional = optional,
				optionalMessage = supportMessage,
			)
		}
	}
}

private fun selectLabelStyle(
	focused: Boolean,
	textNotEmpty: Boolean,
) = if (textNotEmpty || focused) {
	Typography.bodyMedium
} else {
	Typography.bodyLarge
}

@Composable
private fun textInputFieldColors(): TextFieldColors =
	TextFieldDefaults.colors(
		focusedTextColor = MaterialTheme.colorScheme.onSurface,
		unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
		disabledTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
		focusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
		unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
		disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
		errorLabelColor = MaterialTheme.colorScheme.error,
		focusedIndicatorColor = MaterialTheme.colorScheme.primaryContainer,
		unfocusedIndicatorColor = MaterialTheme.colorScheme.primaryContainer,
		disabledIndicatorColor = MaterialTheme.colorScheme.primaryContainer,
		errorIndicatorColor = MaterialTheme.colorScheme.error,
		focusedContainerColor = MaterialTheme.colorScheme.surface,
		unfocusedContainerColor = MaterialTheme.colorScheme.surface,
		disabledContainerColor = MaterialTheme.colorScheme.surfaceDim,
		errorContainerColor = MaterialTheme.colorScheme.surface,
		disabledLeadingIconColor = MaterialTheme.colorScheme.primary,
		focusedLeadingIconColor = MaterialTheme.colorScheme.primary,
		unfocusedLeadingIconColor = MaterialTheme.colorScheme.primary,
		errorLeadingIconColor = MaterialTheme.colorScheme.error,
	)

@Composable
private fun getLeadingIcon(leadingIconId: Int?): @Composable (() -> Unit)? =
	leadingIconId?.let {
		{
			Icon(
				painter = painterResource(id = it),
				contentDescription = null,
			)
		}
	}

@Composable
private fun getErrorIcon(errorVisible: Boolean): @Composable (() -> Unit)? =
	(@Composable {
		Icon(
			painter = painterResource(id = R.drawable.ic_error),
			contentDescription = null,
			tint = MaterialTheme.colorScheme.error,
		)
	}).takeIf { errorVisible }

@Composable
fun LoadingTextInputField(
	paddingValues: PaddingValues = PaddingValues(top = 8.dp, bottom = 20.dp),
) {
	Box(
		modifier = Modifier
			.fillMaxWidth()
			.background(color = Color.Transparent)
			.padding(paddingValues)
			.border(
				width = 1.dp,
				color = MaterialTheme.colorScheme.surfaceContainer,
				shape = MaterialTheme.shapes.medium
			),
	) {
		Row {
			BaseText(
				text = " ",
				style = MaterialTheme.typography.bodyLarge,
				modifier = Modifier
					.padding(vertical = 18.dp)
					.padding(start = 16.dp)
					.shimmerAnimation()
					.width(200.dp),
			)
		}
	}
}

@Preview
@Composable
private fun TextInputFieldPreview() {
	var text by remember { mutableStateOf("") }
	var errorText by remember { mutableStateOf("") }
	val focusManager = LocalFocusManager.current
	Screen {
		Column {
			TextInputField(
				text = text,
				label = "Почта",
				onValueChanged = { text = it },
				errorMessage = errorText,
				leadingIconId = R.drawable.ic_mail,
				modifier = Modifier.padding(bottom = 20.dp),
				keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
			)

			LoadingTextInputField()

			ElevatedButton(
				onClick = { errorText = "Неверная почта".takeIf { errorText.isBlank() } ?: "" },
				modifier = Modifier
					.fillMaxWidth()
					.padding(10.dp)
			) {
				Text("Выставить / убрать ошибку")
			}

			ElevatedButton(
				onClick = { focusManager.clearFocus() },
				modifier = Modifier
					.fillMaxWidth()
					.padding(10.dp),

				) {
				Text("Убрать фокус с поля")
			}
		}
	}
}