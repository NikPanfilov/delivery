package ru.nikpanfilov.delivery.feature.calculation.ui

import android.view.MotionEvent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.nikpanfilov.delivery.core.ui.compose.BodyMediumText
import ru.nikpanfilov.delivery.core.ui.compose.Screen
import ru.nikpanfilov.delivery.core.ui.compose.TextInputField
import ru.nikpanfilov.delivery.feature.calculation.R

@OptIn(ExperimentalComposeUiApi::class)
@Composable
internal fun ColumnScope.Package(
	packageName: String,
	onChangePackageType: () -> Unit,
) {
	Spacer(modifier = Modifier.height(24.dp))

	BodyMediumText(text = stringResource(R.string.package_size))

	Spacer(modifier = Modifier.height(6.dp))

	TextInputField(
		text = packageName,
		leadingIconId = R.drawable.ic_package,
		trailingIconId = R.drawable.ic_arrow_down,
		label = "",
		onValueChanged = {},
		readOnly = true,
		modifier = Modifier.pointerInteropFilter {
			(it.action == MotionEvent.ACTION_DOWN).also {
				if (it) {
					onChangePackageType()
				}
			}
		}
	)
}

@Preview
@Composable
private fun Preview() {
	Screen {
		Column {
			Package(
				packageName = "Конверт",
				onChangePackageType = {},
			)
		}
	}
}