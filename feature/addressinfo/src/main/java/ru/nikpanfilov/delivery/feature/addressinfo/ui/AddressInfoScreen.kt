package ru.nikpanfilov.delivery.feature.addressinfo.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import ru.nikpanfilov.delivery.core.ui.compose.AppBar
import ru.nikpanfilov.delivery.feature.addressinfo.presentation.AddressInfoIntent
import ru.nikpanfilov.delivery.feature.addressinfo.presentation.AddressInfoState
import ru.nikpanfilov.delivery.feature.addressinfo.presentation.InfoPage
import ru.nikpanfilov.delivery.feature.deliveryinfo.R
import ru.nikpanfilov.delivery.core.ui.R as ComponentR

@Composable
fun AddressInfoScreen(
	state: AddressInfoState,
	applyIntent: (AddressInfoIntent) -> Unit,
) {
	Scaffold(
		topBar = {
			AppBar(
				title = stringResource(
					if (state.page == InfoPage.SENDER_ADDRESS) {
						R.string.sender_address
					} else {
						R.string.receiver_address
					}
				),
				leftIcon = ImageVector.vectorResource(ComponentR.drawable.ic_arrow_back),
				onLeftButtonClick = { applyIntent(AddressInfoIntent.NavigateBack) },
			)
		}
	) {
		Box(
			modifier = Modifier
				.fillMaxSize()
				.padding(it),
		) {
			AddressInfoContent(state = state, applyIntent = applyIntent)
		}
	}

	BackHandler {
		applyIntent(AddressInfoIntent.NavigateBack)
	}
}