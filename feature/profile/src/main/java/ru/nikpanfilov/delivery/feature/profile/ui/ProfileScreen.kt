package ru.nikpanfilov.delivery.feature.profile.ui

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
import ru.nikpanfilov.delivery.feature.profile.R
import ru.nikpanfilov.delivery.feature.profile.presentation.ProfileIntent
import ru.nikpanfilov.delivery.feature.profile.presentation.ProfileState
import ru.nikpanfilov.delivery.core.ui.R as ComponentR

@Composable
fun ProfileScreen(
	state: ProfileState,
	applyIntent: (ProfileIntent) -> Unit,
) {
	Scaffold(
		topBar = {
			AppBar(
				title = stringResource(R.string.profile),
				leftIcon = ImageVector.vectorResource(ComponentR.drawable.ic_arrow_back),
				onLeftButtonClick = { applyIntent(ProfileIntent.NavigateBack) },
			)
		}
	) {
		Box(
			modifier = Modifier
				.fillMaxSize()
				.padding(it),
		) {
			when (state) {
				is ProfileState.Initial -> applyIntent(ProfileIntent.LoadData)
				is ProfileState.Loading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
				is ProfileState.Error   -> Error(onTryAgain = { applyIntent(ProfileIntent.LoadData) })
				is ProfileState.Content -> ProfileContent(
					state = state,
					applyIntent = applyIntent,
				)
			}
		}
	}
}