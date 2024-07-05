package ru.nikpanfilov.delivery.core.ui.compose

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SmallButton(
	text: String,
	onClick: () -> Unit,
	modifier: Modifier = Modifier,
	enabled: Boolean = true,
) {
	val colors = ButtonDefaults.buttonColors(
		containerColor = MaterialTheme.colorScheme.secondaryContainer,
		contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
		disabledContainerColor = MaterialTheme.colorScheme.secondary,
		disabledContentColor = MaterialTheme.colorScheme.onSecondary,
	)

	Button(
		modifier = modifier,
		onClick = onClick,
		shape = MaterialTheme.shapes.small,
		colors = colors,
		enabled = enabled,
	) {
		LabelMediumText(text = text)
	}
}

@Composable
@Preview
private fun Preview() {
	Screen {
		SmallButton(text = "Обновить", onClick = {})
	}
}