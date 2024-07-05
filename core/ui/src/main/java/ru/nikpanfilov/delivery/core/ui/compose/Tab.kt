package ru.nikpanfilov.delivery.core.ui.compose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

const val INITIAL_PAGE = 0
const val PAGE_COUNT = 2

data class Tab(val title: String, val content: @Composable () -> Unit)

/**
 * Компонент в фигме:
 * [Figma](https://www.figma.com/file/jBaZDfTxkix3FZ2iNC0oiQ/%D0%9C%D0%BE%D0%B1%D0%B8%D0%BB%D1%8C%D0%BD%D0%BE%D0%B5-%D0%BF%D1%80%D0%B8%D0%BB%D0%BE%D0%B6%D0%B5%D0%BD%D0%B8%D0%B5-%D0%A8%D0%98%D0%A4%D0%A2-%D0%98%D0%BD%D1%82%D0%B5%D0%BD%D1%81%D0%B8%D0%B2?type=design&node-id=1078-4954&mode=design&t=8opEYegV5n046yLW-4)
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Tabs(
	tabs: List<Tab>,
	modifier: Modifier = Modifier,
	initialPage: Int = INITIAL_PAGE,
	onPageChange: ((Int) -> Unit)? = null,
) {
	val pagerState: PagerState = rememberPagerState(
		initialPage = initialPage,
		initialPageOffsetFraction = 0f,
		pageCount = { PAGE_COUNT },
	)
	LaunchedEffect(pagerState.currentPage) {
		onPageChange?.invoke(pagerState.currentPage)
	}

	val coroutineScope = rememberCoroutineScope()
	Column(modifier) {
		TabRow(
			selectedTabIndex = pagerState.currentPage,
			divider = { HorizontalDivider(thickness = 2.dp, color = MaterialTheme.colorScheme.secondary) },
			indicator = { Indicator(pagerState.currentPage, it) }, //В чём разница с дефолтным? Толщина меньше на 1dp
		) {
			tabs.forEachIndexed { index, tab ->
				Tab(
					text = { TabTitle(tab.title, pagerState.currentPage == index) },
					selected = pagerState.currentPage == index,
					onClick = {
						coroutineScope.launch {
							pagerState.animateScrollToPage(index)
						}
					},
					selectedContentColor = MaterialTheme.colorScheme.primary,
					unselectedContentColor = MaterialTheme.colorScheme.onBackground,
				)
			}
		}

		HorizontalPager(
			state = pagerState,
			beyondBoundsPageCount = 1,
		) {
			tabs[it].content()
		}
	}
}

@Composable
private fun Indicator(
	currentPage: Int,
	tabPositions: List<TabPosition>,
) {
	if (currentPage < tabPositions.size) {
		TabRowDefaults.SecondaryIndicator(
			modifier = Modifier.tabIndicatorOffset(tabPositions[currentPage]),
			height = 2.dp,
		)
	}
}

@Composable
private fun TabTitle(
	title: String,
	selected: Boolean,
) {
	val color = if (selected) {
		MaterialTheme.colorScheme.primary
	} else {
		MaterialTheme.colorScheme.onSurfaceVariant
	}

	Text(
		text = title,
		style = MaterialTheme.typography.titleSmall,
		color = color,
		modifier = Modifier.padding(bottom = 2.dp),
	)
}

@Preview
@Composable
private fun Preview() {
	Screen {
		Tabs(
			tabs = listOf(
				Tab(title = "Кураторы") {
					Column(modifier = Modifier.fillMaxSize()) {
						for (i in 1..5)
							Text(text = "Curators $i")
						LazyColumn {
							items(50) {
								Text(text = "Curators Text")
							}
						}
					}
				},
				Tab("Команда") {
					Column(modifier = Modifier.fillMaxSize()) {
						for (i in 1..5)
							Text(text = "Team $i")
						LazyColumn {
							items(30) {
								Text(text = "Team Text")
							}
						}
					}
				}
			),
			onPageChange = {}
		)
	}
}
