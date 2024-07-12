package ru.nikpanfilov.delivery.feature.signin.ui

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
import ru.nikpanfilov.delivery.feature.signin.R
import ru.nikpanfilov.delivery.feature.signin.presentation.LoadingStatus
import ru.nikpanfilov.delivery.feature.signin.presentation.SignInIntent
import ru.nikpanfilov.delivery.feature.signin.presentation.SignInState
import ru.nikpanfilov.delivery.core.ui.R as ComponentR

@Composable
fun SignInScreen(
	state: SignInState,
	applyIntent: (SignInIntent) -> Unit,
) {
	Scaffold(
		topBar = {
			AppBar(
				title = stringResource(R.string.sign_in),
				leftIcon = ImageVector.vectorResource(ComponentR.drawable.ic_dismiss),
				onLeftButtonClick = {
					applyIntent(SignInIntent.NavigateBack)
				},
			)
		}
	) {
		Box(
			modifier = Modifier
				.fillMaxSize()
				.padding(it),
		) {
			when (state.loadingStatus) {
				is LoadingStatus.Loading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
				is LoadingStatus.Error   -> Error(onTryAgain = { applyIntent(SignInIntent.Next) })
				is LoadingStatus.None    -> SignInContent(state = state, applyIntent = applyIntent)
			}
		}
	}
}