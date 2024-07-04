package ru.nikpanfilov.delivery.core.ui.compose

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomCheckbox(
	checked: Boolean,
	onCheckChange: (Boolean) -> Unit,
	modifier: Modifier = Modifier,
) {
	CompositionLocalProvider(
		LocalMinimumInteractiveComponentEnforcement provides false,
	) {
		val checkboxColors = CheckboxDefaults.colors(
			checkedColor = Color.Transparent,
			uncheckedColor = Color.Transparent,
			checkmarkColor = MaterialTheme.colorScheme.primary,
		)

		Checkbox(
			checked = checked,
			onCheckedChange = onCheckChange,
			modifier = modifier
				.border(
					width = 1.dp,
					color = MaterialTheme.colorScheme.secondary,
					shape = RoundedCornerShape(5.dp),
				),
			colors = checkboxColors,
		)
	}
}

@Preview
@Composable
private fun Preview() {
	Screen {
		CustomCheckbox(
			checked = true,
			onCheckChange = {},
			modifier = Modifier.size(20.dp),
		)
	}
}