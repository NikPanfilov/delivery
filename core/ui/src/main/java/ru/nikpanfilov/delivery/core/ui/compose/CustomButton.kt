package ru.nikpanfilov.delivery.core.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.nikpanfilov.delivery.core.ui.theme.AppTheme

/**
 * Компонент в фигме:
 * [Figma](https://www.figma.com/file/jBaZDfTxkix3FZ2iNC0oiQ/%D0%9C%D0%BE%D0%B1%D0%B8%D0%BB%D1%8C%D0%BD%D0%BE%D0%B5-%D0%BF%D1%80%D0%B8%D0%BB%D0%BE%D0%B6%D0%B5%D0%BD%D0%B8%D0%B5-%D0%A8%D0%98%D0%A4%D0%A2-%D0%98%D0%BD%D1%82%D0%B5%D0%BD%D1%81%D0%B8%D0%B2?type=design&node-id=2159-3035&mode=design&t=GOPA8sXlY2WQ4pk3-4)
 */
@Composable
fun CustomButton(
	text: String,
	onClick: () -> Unit,
	modifier: Modifier = Modifier,
	containerColor: Color = MaterialTheme.colorScheme.primary,
	enabled: Boolean = true,
) {
	val colors = ButtonDefaults.buttonColors(
		containerColor = containerColor,
		contentColor = MaterialTheme.colorScheme.onPrimary,
		disabledContainerColor = MaterialTheme.colorScheme.secondary,
		disabledContentColor = MaterialTheme.colorScheme.onSecondary,
	)

	Button(
		onClick = onClick,
		modifier = modifier
			.fillMaxWidth()
			.height(53.dp),
		enabled = enabled,
		shape = MaterialTheme.shapes.medium,
		colors = colors,
	) {
		LabelLargeText(text = text)
	}
}

@Preview
@Composable
private fun CustomButtonPreview() {
	AppTheme {
		Surface {
			Column {
				CustomButton(
					text = "Кнопка",
					onClick = {},
				)

				CustomButton(
					text = "не активная кнопка",
					onClick = {},
					modifier = Modifier.padding(top = 30.dp),
					enabled = false,
				)
			}
		}
	}
}