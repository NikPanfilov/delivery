package ru.nikpanfilov.delivery.core.ui.compose

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.nikpanfilov.delivery.core.ui.R

@Composable
internal fun SupportingText(
	errorMessage: String,
	optional: Boolean,
	optionalMessage: String? = null,
	expanded: Boolean = false,
) {
	Column(
		modifier = Modifier.defaultMinSize(minHeight = 20.dp),
	) {
		if (!expanded && errorMessage.isNotEmpty()) {
			ErrorText(
				text = errorMessage,
				modifier = Modifier
					.padding(horizontal = 15.dp)
					.padding(top = 4.dp),
			)

		} else if (!expanded && optional) {
			HelperText(
				text = optionalMessage ?: stringResource(id = R.string.optional),
				modifier = Modifier
					.padding(horizontal = 15.dp)
					.padding(top = 4.dp),
			)
		}
	}
}

@Preview
@Composable
private fun PreviewSupportingText() {
	Screen {
		Column {
			Box(Modifier.border(1.dp, Color.Black)) {
				SupportingText(errorMessage = "какая-нибудь ошибка", optional = false, expanded = false, optionalMessage = "")
			}
			Box(Modifier.border(1.dp, Color.Black)) {
				SupportingText(errorMessage = "", optional = true, optionalMessage = "")
			}
			Box(Modifier.border(1.dp, Color.Black)) {
				SupportingText(errorMessage = "", optional = false, optionalMessage = "Администратор запретил изменять это поле")
			}
		}
	}
}