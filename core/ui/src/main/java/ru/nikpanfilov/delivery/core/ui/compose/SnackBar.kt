package ru.nikpanfilov.delivery.core.ui.compose

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import ru.nikpanfilov.delivery.core.ui.R

/**
 * Компонент в фигме:
 * [Figma](https://www.figma.com/file/jBaZDfTxkix3FZ2iNC0oiQ/%D0%9C%D0%BE%D0%B1%D0%B8%D0%BB%D1%8C%D0%BD%D0%BE%D0%B5-%D0%BF%D1%80%D0%B8%D0%BB%D0%BE%D0%B6%D0%B5%D0%BD%D0%B8%D0%B5-%D0%A8%D0%98%D0%A4%D0%A2-%D0%98%D0%BD%D1%82%D0%B5%D0%BD%D1%81%D0%B8%D0%B2?type=design&node-id=5806-17708&mode=design&t=MOIC9Aw8fgvrOmrh-4)
 */

@Composable
fun SnackBarHost(
	snackBarHostState: SnackbarHostState,
	onAction: (() -> Unit)? = null,
) {
	SnackbarHost(
		hostState = snackBarHostState,
		snackbar = {
			SnackBar(
				snackBarData = it,
				snackBarHostState = snackBarHostState,
				onAction = onAction
			)
		})
}

fun CoroutineScope.showCustomSnackBar(
	snackBarHostState: SnackbarHostState,
	message: String,
	actionLabel: String? = null,
	duration: SnackbarDuration =
		if (actionLabel == null) SnackbarDuration.Short else SnackbarDuration.Indefinite,
	withDismissAction: Boolean = false,
) {
	this.launch {
		snackBarHostState.showSnackbar(
			message = message,
			actionLabel = actionLabel,
			withDismissAction = withDismissAction,
			duration = duration,
		)
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SnackBar(
	snackBarData: SnackbarData,
	snackBarHostState: SnackbarHostState,
	modifier: Modifier = Modifier,
	onAction: (() -> Unit)? = null,
	onDismiss: (() -> Unit)? = null,
) {
	val swipeToDismissState = rememberSwipeToDismissBoxState(
		confirmValueChange = {
			if (it == SwipeToDismissBoxValue.EndToStart || it == SwipeToDismissBoxValue.StartToEnd) {
				snackBarHostState.currentSnackbarData?.dismiss()
				return@rememberSwipeToDismissBoxState true
			} else {
				return@rememberSwipeToDismissBoxState false
			}
		}
	)

	SwipeToDismissBox(state = swipeToDismissState, backgroundContent = {}) {
		Surface(
			color = MaterialTheme.colorScheme.surface,
			shadowElevation = 6.dp,
			modifier = modifier
				.padding(horizontal = 16.dp, vertical = 8.dp)
				.fillMaxWidth()
				.heightIn(min = 68.dp),
			shape = RoundedCornerShape(size = 4.dp),
		) {
			Row(
				verticalAlignment = Alignment.CenterVertically,
				modifier = Modifier
					.fillMaxWidth()
					.padding(start = 16.dp),
			) {
				BodyMediumText(
					text = snackBarData.visuals.message,
					color = MaterialTheme.colorScheme.onSurface,
					modifier = Modifier.weight(0.3f),
				)

				snackBarData.visuals.actionLabel?.let {
					TextButton(
						onClick = {
							onAction?.invoke()
							snackBarHostState.currentSnackbarData?.performAction()
						},
					) {
						BaseText(
							text = it,
							style = MaterialTheme.typography.bodyMedium,
							color = MaterialTheme.colorScheme.primary,
						)
					}
				}

				if (snackBarData.visuals.withDismissAction) {
					IconButton(
						onClick = {
							onDismiss?.invoke()
							snackBarHostState.currentSnackbarData?.dismiss()
						},
					) {
						Icon(
							imageVector = ImageVector.vectorResource(id = R.drawable.ic_dismiss),
							contentDescription = null,
						)
					}
				}
			}
		}
	}
}

@Preview
@Composable
private fun SnackBarPreview() {
	val snackBarHostState = SnackbarHostState()
	val coroutineScope = rememberCoroutineScope()

	Screen {
		Scaffold(
			snackbarHost = {
				SnackBarHost(
					snackBarHostState = snackBarHostState,
					onAction = { Log.d("CustomSnackBar", "action button clicked") })
			},
			topBar = {
				AppBar(
					title = "SnackBarTest",
				)
			}
		) { paddingValues ->
			Column(
				modifier = Modifier
					.fillMaxSize()
					.verticalScroll(rememberScrollState())
					.padding(paddingValues)
			) {
				CustomButton(
					text = "No action SnackBar",
					onClick = {
						coroutineScope.launch {
							showCustomSnackBar(
								snackBarHostState = snackBarHostState,
								message = "Two-line snackbar with action and close affordance",
								withDismissAction = true,
							)
						}
					},
					modifier = Modifier.padding(vertical = 8.dp, horizontal = 8.dp),
				)

				CustomButton(
					text = "No dismissAction snackBar",
					onClick = {
						coroutineScope.launch {
							showCustomSnackBar(
								snackBarHostState = snackBarHostState,
								message = "Two-line snackbar with action and close affordance",
								actionLabel = "Action",
							)
						}
					},
					modifier = Modifier
						.padding(bottom = 8.dp)
						.padding(horizontal = 8.dp),
				)

				CustomButton(
					text = "SnackBar with text only",
					onClick = {
						coroutineScope.launch {
							showCustomSnackBar(
								snackBarHostState = snackBarHostState,
								message = "Two-line snackbar with action and close affordance",
							)
						}
					},
					modifier = Modifier
						.padding(bottom = 8.dp)
						.padding(horizontal = 8.dp),
				)

				CustomButton(
					text = "SnackBar wit action and dismiss",
					onClick = {
						coroutineScope.launch {
							showCustomSnackBar(
								snackBarHostState = snackBarHostState,
								message = "Two-line snackbar with action and close affordance",
								actionLabel = "Action",
								withDismissAction = true,
							)
						}
					},
					modifier = Modifier
						.padding(bottom = 8.dp)
						.padding(horizontal = 8.dp),
				)
			}
		}
	}
}