package ru.nikpanfilov.delivery.feature.calculation.ui

import android.view.MotionEvent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.nikpanfilov.delivery.core.ui.compose.BaseText
import ru.nikpanfilov.delivery.core.ui.compose.BodyMediumText
import ru.nikpanfilov.delivery.core.ui.compose.Screen
import ru.nikpanfilov.delivery.core.ui.compose.TextInputField
import ru.nikpanfilov.delivery.feature.calculation.R
import ru.nikpanfilov.delivery.shared.cities.domain.entity.DeliveryPoint

@OptIn(ExperimentalLayoutApi::class, ExperimentalComposeUiApi::class)
@Composable
internal fun ColumnScope.DeliveryCity(
	cityTitle: String,
	cityName: String,
	cityIcon: Int,
	citiesOptions: List<DeliveryPoint>,
	onChangeCity: () -> Unit,
	onSetCity: (DeliveryPoint) -> Unit,
) {
	Spacer(modifier = Modifier.height(24.dp))

	BodyMediumText(text = cityTitle)

	Spacer(modifier = Modifier.height(6.dp))

	TextInputField(
		text = cityName,
		leadingIconId = cityIcon,
		trailingIconId = R.drawable.ic_arrow_down,
		label = "",
		onValueChanged = {},
		readOnly = true,
		modifier = Modifier.pointerInteropFilter {
			(it.action == MotionEvent.ACTION_DOWN).also {
				if (it) {
					onChangeCity()
				}
			}
		}
	)

	FlowRow {
		citiesOptions.forEach {
			BaseText(
				text = it.name,
				style = MaterialTheme.typography.bodyMedium.copy(
					fontStyle = FontStyle.Italic,
					textDecoration = TextDecoration.Underline,
				),
				modifier = Modifier
					.padding(top = 2.dp, end = 8.dp)
					.clickable { onSetCity(it) },
			)
		}
	}
}

@Preview
@Composable
private fun Preview() {
	Screen {
		Column {
			val options = listOf(
				DeliveryPoint(
					id = "0",
					name = "Город1",
					latitude = 1.0,
					longitude = 1.0,
				),
				DeliveryPoint(
					id = "0",
					name = "Город2",
					latitude = 1.0,
					longitude = 1.0,
				),
				DeliveryPoint(
					id = "0",
					name = "ГородГородГородГород",
					latitude = 1.0,
					longitude = 1.0,
				),
			)
			DeliveryCity(
				cityTitle = "Город отправления",
				cityName = "Город",
				cityIcon = R.drawable.ic_sender_city,
				citiesOptions = options,
				onChangeCity = {},
				onSetCity = {},
			)
		}
	}
}