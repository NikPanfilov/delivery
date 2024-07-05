package ru.nikpanfilov.delivery.core.ui.compose

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue

@Composable
fun EnableKeyBoardScrollListener(scrollState: ScrollState) {
	val imeState by rememberKeyboardImeState()
	LaunchedEffect(imeState) {
		if (imeState) {
			scrollState.animateScrollTo(scrollState.maxValue, tween(200))
		}
	}
}