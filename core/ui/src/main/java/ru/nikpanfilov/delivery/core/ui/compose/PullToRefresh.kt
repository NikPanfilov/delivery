package ru.nikpanfilov.delivery.core.ui.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PullToRefresh(
	onRefresh: () -> Unit,
	modifier: Modifier = Modifier,
	enabled: Boolean = true,
	contentPaddingValues: PaddingValues = PaddingValues(0.dp),
	content: @Composable () -> Unit,
) {
	val pullToRefreshState = rememberPullToRefreshState(enabled = { enabled })
	if (pullToRefreshState.isRefreshing) {
		LaunchedEffect(true) {
			onRefresh()
			pullToRefreshState.endRefresh()
		}
	}
	Box(modifier = modifier.nestedScroll(pullToRefreshState.nestedScrollConnection)) {
		Box(modifier = Modifier.padding(paddingValues = contentPaddingValues)) {
			content()
		}

		PullToRefreshContainer(
			modifier = Modifier.align(Alignment.TopCenter),
			state = pullToRefreshState,
		)
	}
}

@Composable
@Preview
private fun Preview() {
	val count = remember {
		mutableIntStateOf(5)
	}
	val scroll = rememberScrollState()

	Screen {
		PullToRefresh(
			onRefresh = { count.value++ },
			contentPaddingValues = PaddingValues(horizontal = 14.dp)
		) {
			Column(
				modifier = Modifier
					.fillMaxSize()
					.verticalScroll(scroll)
			) {
				repeat(count.value) {
					BodyMediumText(
						text = "Some text",
						modifier = Modifier.padding(top = 8.dp),
					)
				}
			}
		}
	}
}