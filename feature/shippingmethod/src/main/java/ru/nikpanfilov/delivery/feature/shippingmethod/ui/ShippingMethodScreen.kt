package ru.nikpanfilov.delivery.feature.shippingmethod.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import ru.nikpanfilov.delivery.core.ui.compose.AppBar
import ru.nikpanfilov.delivery.feature.shippingmethod.R
import ru.nikpanfilov.delivery.feature.shippingmethod.presentation.ShippingMethodIntent
import ru.nikpanfilov.delivery.feature.shippingmethod.presentation.ShippingMethodState
import ru.nikpanfilov.delivery.shared.deliveryinfo.domain.entity.DeliveryOption
import ru.nikpanfilov.delivery.core.ui.R as ComponentR

@Composable
fun ShippingMethodScreen(
	state: ShippingMethodState,
	applyIntent: (ShippingMethodIntent) -> Unit,
) {
	Scaffold(
		topBar = {
			AppBar(
				title = stringResource(R.string.shipping_method),
				leftIcon = ImageVector.vectorResource(ComponentR.drawable.ic_dismiss),
				onLeftButtonClick = { applyIntent(ShippingMethodIntent.NavigateBack) },
			)
		}
	) {
		LazyColumn(
			modifier = Modifier
				.fillMaxSize()
				.padding(it),
			contentPadding = PaddingValues()
		) {
			item {
				Spacer(modifier = Modifier.height(24.dp))
			}

			items(state.options, key = DeliveryOption::id) {
				DeliveryOptionItem(
					option = it,
					onChooseOption = { applyIntent(ShippingMethodIntent.ChooseDeliveryOption(it)) },
				)
			}
		}
	}
}