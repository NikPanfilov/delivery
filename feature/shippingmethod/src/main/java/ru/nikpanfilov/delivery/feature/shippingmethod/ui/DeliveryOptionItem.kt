package ru.nikpanfilov.delivery.feature.shippingmethod.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.nikpanfilov.delivery.core.ui.compose.BodyLargeText
import ru.nikpanfilov.delivery.core.ui.compose.BodySmallText
import ru.nikpanfilov.delivery.core.ui.compose.Screen
import ru.nikpanfilov.delivery.core.ui.compose.clickableArea
import ru.nikpanfilov.delivery.feature.shippingmethod.R
import ru.nikpanfilov.delivery.shared.deliveryinfo.domain.entity.DeliveryOption
import ru.nikpanfilov.delivery.shared.deliveryinfo.domain.entity.DeliveryType
import kotlin.math.roundToInt

@Composable
internal fun DeliveryOptionItem(
	option: DeliveryOption,
	onChooseOption: () -> Unit,
) {
	Card(
		modifier = Modifier
			.fillMaxWidth()
			.padding(horizontal = 16.dp)
			.padding(bottom = 24.dp)
			.clickableArea { onChooseOption() },
		colors = CardDefaults.cardColors().copy(
			containerColor = MaterialTheme.colorScheme.surface,
		),
		border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary),
	) {
		Row(
			modifier = Modifier.padding(
				start = 16.dp,
				end = 24.dp,
				top = 16.dp,
				bottom = 16.dp,
			)
		) {
			Icon(
				painter = painterResource(
					if (option.type == DeliveryType.EXPRESS) {
						R.drawable.ic_express
					} else {
						R.drawable.ic_default
					}
				),
				tint = Color.Unspecified,
				contentDescription = null,
			)

			Spacer(modifier = Modifier.width(16.dp))

			Column {
				BodySmallText(text = option.name)

				Spacer(modifier = Modifier.height(8.dp))

				BodyLargeText(text = stringResource(R.string.price, option.price))

				Spacer(modifier = Modifier.height(24.dp))

				BodyLargeText(text = pluralStringResource(R.plurals.days, option.days.roundToInt(), option.days.roundToInt()))
			}

			Spacer(modifier = Modifier.weight(1f))

			Icon(painter = painterResource(R.drawable.ic_next), contentDescription = null)
		}
	}
}

@Preview
@Composable
private fun Preview() {
	Screen {
		DeliveryOptionItem(
			option = DeliveryOption(
				id = "1",
				price = 100.50,
				days = 7.0,
				name = "Имя доставки",
				type = DeliveryType.EXPRESS,
			),
			onChooseOption = { },
		)
	}
}