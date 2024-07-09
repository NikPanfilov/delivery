package ru.nikpanfilov.delivery.feature.calculation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.nikpanfilov.delivery.core.ui.compose.CustomButton
import ru.nikpanfilov.delivery.core.ui.compose.Screen
import ru.nikpanfilov.delivery.core.ui.compose.TitleLargeText
import ru.nikpanfilov.delivery.feature.calculation.R
import ru.nikpanfilov.delivery.feature.calculation.domain.entity.PackageType
import ru.nikpanfilov.delivery.feature.calculation.presentation.CalculationIntent
import ru.nikpanfilov.delivery.feature.calculation.presentation.CalculationState
import ru.nikpanfilov.delivery.shared.cities.domain.entity.DeliveryPoint

@Composable
internal fun CalculationContent(
	state: CalculationState.Content,
	applyIntent: (CalculationIntent) -> Unit,
) {
	Box(
		modifier = Modifier.fillMaxSize(),
		contentAlignment = Alignment.Center,
	) {
		Card(
			modifier = Modifier
				.padding(horizontal = 16.dp)
				.wrapContentHeight(),
		) {
			Column(
				modifier = Modifier.padding(
					horizontal = 16.dp,
					vertical = 32.dp,
				),
			) {
				TitleLargeText(text = stringResource(R.string.calculate_delivery))

				DeliveryCity(
					cityTitle = stringResource(R.string.sender_city),
					cityName = state.sendPoint.name,
					cityIcon = R.drawable.ic_sender_city,
					citiesOptions = state.deliveryPointOptions.take(3),
					onChangeCity = { applyIntent(CalculationIntent.ChangeSenderCity) },
					onSetCity = { applyIntent(CalculationIntent.SetSenderCity(it)) },
				)

				DeliveryCity(
					cityTitle = stringResource(R.string.receiver_city),
					cityName = state.receiverPoint.name,
					cityIcon = R.drawable.ic_receiver_city,
					citiesOptions = state.deliveryPointOptions.take(3),
					onChangeCity = { applyIntent(CalculationIntent.ChangeReceiverCity) },
					onSetCity = { applyIntent(CalculationIntent.SetReceiverCity(it)) },
				)

				Package(
					packageName = state.packageType.name,
					onChangePackageType = { applyIntent(CalculationIntent.ChangePackageType) },
				)

				Spacer(modifier = Modifier.height(24.dp))

				CustomButton(
					text = stringResource(R.string.calculate),
					onClick = { applyIntent(CalculationIntent.Calculate) },
				)
			}
		}

		ChangeDetails(
			detailsChange = state.detailsChange,
			deliveryPoints = state.deliveryPointOptions,
			packageTypes = state.packageTypeOptions,
			onCancelChangeDetails = { applyIntent(CalculationIntent.CancelChangeDetails) },
			onSetSenderCity = { applyIntent(CalculationIntent.SetSenderCity(it)) },
			onSetReceiverCity = { applyIntent(CalculationIntent.SetReceiverCity(it)) },
			onSetPackageType = { applyIntent(CalculationIntent.SetPackageType(it)) },
		)
	}
}

@Preview
@Composable
private fun Preview() {
	Screen {
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
				name = "Город3",
				latitude = 1.0,
				longitude = 1.0,
			),
			DeliveryPoint(
				id = "0",
				name = "Город4",
				latitude = 1.0,
				longitude = 1.0,
			),
			DeliveryPoint(
				id = "0",
				name = "Город5",
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
		val packages = listOf(
			PackageType(
				id = "",
				name = "Тип1",
				height = 1.0,
				width = 1.0,
				length = 1.0,
				weight = 1.0,
			),
		)

		CalculationContent(
			state = CalculationState.Content(
				sendPoint = options.first(),
				receiverPoint = options.last(),
				deliveryPointOptions = options,
				packageType = packages.first(),
				packageTypeOptions = packages,
				detailsChange = null,
			),
			applyIntent = {},
		)
	}
}
