package ru.nikpanfilov.delivery.core.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun OutlinedTextFieldBackground(
	enabledColor: Color,
	disabledColor: Color,
	enabled: Boolean,
	content: @Composable () -> Unit,
) {
	Box {
		Box(
			modifier = Modifier
				.matchParentSize()
				.padding(top = 8.dp)
				.background(
					color = if (enabled) {
						enabledColor
					} else {
						disabledColor
					},
					shape = MaterialTheme.shapes.medium
				)
		)

		content()
	}
}