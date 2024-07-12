package ru.nikpanfilov.delivery.feature.personalinfo.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import ru.nikpanfilov.delivery.core.ui.compose.AppBar
import ru.nikpanfilov.delivery.core.ui.compose.Error
import ru.nikpanfilov.delivery.feature.personalinfo.R
import ru.nikpanfilov.delivery.feature.personalinfo.presentation.InfoPage
import ru.nikpanfilov.delivery.feature.personalinfo.presentation.PersonalInfoIntent
import ru.nikpanfilov.delivery.feature.personalinfo.presentation.PersonalInfoState
import ru.nikpanfilov.delivery.core.ui.R as ComponentR

@Composable
fun PersonalInfoScreen(
	state: PersonalInfoState,
	applyIntent: (PersonalInfoIntent) -> Unit,
) {
	Scaffold(
		topBar = {
			AppBar(
				title = stringResource(
					if ((state as? PersonalInfoState.Content)?.page == InfoPage.RECEIVER_INFO) {
						R.string.receiver
					} else {
						R.string.sender
					}
				),
				leftIcon = ImageVector.vectorResource(ComponentR.drawable.ic_arrow_back),
				onLeftButtonClick = { applyIntent(PersonalInfoIntent.NavigateBack) },
			)
		}
	) {
		Box(
			modifier = Modifier
				.fillMaxSize()
				.padding(it),
		) {
			when (state) {
				is PersonalInfoState.Initial -> applyIntent(PersonalInfoIntent.LoadData)
				is PersonalInfoState.Loading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
				is PersonalInfoState.Content -> ProfileInfoContent(state = state, applyIntent = applyIntent)
				is PersonalInfoState.Error   -> Error(onTryAgain = { applyIntent(PersonalInfoIntent.LoadData) })
			}
		}
	}
}