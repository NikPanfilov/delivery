package ru.nikpanfilov.delivery.feature.calculation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import ru.nikpanfilov.delivery.core.ui.compose.Error
import ru.nikpanfilov.delivery.feature.calculation.R
import ru.nikpanfilov.delivery.feature.calculation.presentation.CalculationIntent
import ru.nikpanfilov.delivery.feature.calculation.presentation.CalculationState

@Composable
fun CalculationScreen(
	state: CalculationState,
	applyIntent: (CalculationIntent) -> Unit,
) {
	Box {
		Image(
			painter = painterResource(R.drawable.background),
			contentDescription = null,
			contentScale = ContentScale.FillBounds,
			modifier = Modifier.fillMaxSize(),
		)

		when (state) {
			is CalculationState.Initial -> {
				applyIntent(CalculationIntent.LoadData)
			}

			is CalculationState.Loading -> {
				CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
			}

			is CalculationState.Content -> {
				CalculationContent(
					state = state,
					applyIntent = applyIntent,
				)
			}

			is CalculationState.Error   -> {
				Error(onTryAgain = { applyIntent(CalculationIntent.LoadData) })
			}
		}
	}
}