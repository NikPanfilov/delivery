package ru.nikpanfilov.delivery.feature.calculation.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import ru.nikpanfilov.delivery.core.ui.compose.BodyMediumText
import ru.nikpanfilov.delivery.feature.calculation.R
import ru.nikpanfilov.delivery.feature.calculation.domain.entity.PackageType
import ru.nikpanfilov.delivery.feature.calculation.presentation.DetailsChange
import ru.nikpanfilov.delivery.shared.cities.domain.entity.DeliveryPoint

@Composable
internal fun ChangeDetails(
	detailsChange: DetailsChange?,
	deliveryPoints: List<DeliveryPoint>,
	packageTypes: List<PackageType>,
	onCancelChangeDetails: () -> Unit,
	onSetSenderCity: (DeliveryPoint) -> Unit,
	onSetReceiverCity: (DeliveryPoint) -> Unit,
	onSetPackageType: (PackageType) -> Unit,
) {
	detailsChange?.let {
		when (it) {
			DetailsChange.SEND_POINT     -> {
				ChangeBottomsheet(
					title = stringResource(R.string.sender_city),
					options = deliveryPoints,
					onDismiss = onCancelChangeDetails,
					item = {
						TextButton(
							onClick = { onSetSenderCity(it) },
							modifier = Modifier.fillMaxWidth(),
							shape = RectangleShape,
						) {
							BodyMediumText(text = it.name)
						}
					},
				)
			}

			DetailsChange.RECEIVER_POINT -> {
				ChangeBottomsheet(
					title = stringResource(R.string.receiver_city),
					options = deliveryPoints,
					onDismiss = onCancelChangeDetails,
					item = {
						TextButton(
							onClick = { onSetReceiverCity(it) },
							modifier = Modifier.fillMaxWidth(),
							shape = RectangleShape,
						) {
							BodyMediumText(text = it.name)
						}
					},
				)
			}

			DetailsChange.PACKAGE_TYPE   -> {
				ChangeBottomsheet(
					title = stringResource(R.string.package_size),
					options = packageTypes,
					onDismiss = onCancelChangeDetails,
					item = {
						TextButton(
							onClick = { onSetPackageType(it) },
							modifier = Modifier.fillMaxWidth(),
							shape = RectangleShape,
						) {
							BodyMediumText(text = it.name)
						}
					},
				)
			}
		}
	}
}