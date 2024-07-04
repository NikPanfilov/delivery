package ru.nikpanfilov.delivery.core.ui.compose

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember

@Composable
fun Pagination(
	lazyListState: LazyListState,
	itemsCount: Int,
	onLoadMore: () -> Unit,
	loadThreshold: Int = 6,
) {
	val stateInfo by remember { derivedStateOf { lazyListState.layoutInfo } }
	val lastIndex = stateInfo.visibleItemsInfo.lastOrNull()?.index ?: Int.MIN_VALUE
	if (lastIndex >= (itemsCount - loadThreshold) && itemsCount != 0) {
		LaunchedEffect(itemsCount) {
			onLoadMore()
		}
	}
}