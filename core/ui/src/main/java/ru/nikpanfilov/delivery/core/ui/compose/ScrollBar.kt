package ru.nikpanfilov.delivery.core.ui.compose

import androidx.compose.foundation.ScrollState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.withScrollbar(
	state: ScrollState,
	itemsCount: Int,
	knobColor: Color,
	thickness: Dp = 4.dp,
	visibleItemsCount: Int = 4,
	paddingTop: Dp = 8.dp,
	paddingEnd: Dp = 8.dp,
	paddingBottom: Dp = 18.dp,
): Modifier =
	if (itemsCount > visibleItemsCount) {
		drawWithContent {
			drawContent()
			val viewportHeight = state.viewportSize.toFloat()
			val itemSize = viewportHeight / visibleItemsCount
			val fullListSize = itemSize * itemsCount
			val scrollOffset = state.value.toFloat()
			val knobPosition = (viewportHeight / fullListSize) * scrollOffset + paddingTop.toPx()
			val knobSize = (viewportHeight * viewportHeight) / fullListSize - paddingBottom.toPx()

			drawRoundRect(
				color = knobColor,
				topLeft = Offset(size.width - thickness.toPx() - paddingEnd.toPx(), knobPosition),
				size = Size(thickness.toPx(), knobSize),
				cornerRadius = CornerRadius(x = 4.dp.toPx(), y = 4.dp.toPx()),
			)
		}
	} else {
		drawWithContent { drawContent() }
	}
