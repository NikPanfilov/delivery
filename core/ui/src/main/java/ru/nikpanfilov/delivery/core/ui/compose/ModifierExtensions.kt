package ru.nikpanfilov.delivery.core.ui.compose

import androidx.compose.foundation.ScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun Modifier.scrollToElement(scrollState: ScrollState): Modifier {
	var elementCoordinates by remember { mutableFloatStateOf(0F) }
	var focused by remember { mutableStateOf(false) }
	val coroutineScope = rememberCoroutineScope()
	val imeState by rememberKeyboardImeState()
	return this
		.onGloballyPositioned {
			coroutineScope.launch {
				if (imeState && focused) {
					elementCoordinates = it.positionInParent().y
					scrollState.animateScrollTo(elementCoordinates.roundToInt())
				}
			}
		}
		.onFocusChanged {
			focused = it.isFocused
		}
}