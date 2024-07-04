package ru.nikpanfilov.delivery.core.ui.compose

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import ru.nikpanfilov.delivery.core.ui.R
import ru.nikpanfilov.delivery.core.ui.ext.applyIf
import ru.nikpanfilov.delivery.core.ui.ext.shimmerAnimation
import ru.nikpanfilov.delivery.core.ui.ext.toDp
import ru.nikpanfilov.delivery.core.ui.theme.AppTheme
import ru.nikpanfilov.delivery.core.ui.theme.Typography
import java.lang.Float.min

/**
 * Компонент в фигме:
 * [Figma](https://www.figma.com/file/jBaZDfTxkix3FZ2iNC0oiQ/%D0%9C%D0%BE%D0%B1%D0%B8%D0%BB%D1%8C%D0%BD%D0%BE%D0%B5-%D0%BF%D1%80%D0%B8%D0%BB%D0%BE%D0%B6%D0%B5%D0%BD%D0%B8%D0%B5-%D0%A8%D0%98%D0%A4%D0%A2-%D0%98%D0%BD%D1%82%D0%B5%D0%BD%D1%81%D0%B8%D0%B2?type=design&node-id=844-5577&mode=design&t=RY4YrSxA4vbx7IGN-4)
 */
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun DropdownText(
	options: List<String>,
	label: String,
	scrollState: ScrollState,
	onValueSelect: (String) -> Unit,
	modifier: Modifier = Modifier,
	selectedValue: String = "",
	errorMessage: String = "",
	optionalMessage: String? = null,
	enabled: Boolean = true,
	optional: Boolean = false,
	visibilitySupportingText: Boolean = true,
) {
	var textFieldHeight by remember { mutableStateOf(0.dp) }
	var expanded by remember { mutableStateOf(false) }

	var elementCoordinates by remember { mutableFloatStateOf(0F) }
	var elementCoordinatesInWindow by remember { mutableFloatStateOf(0F) }

	val heightScreenInPx = with(LocalDensity.current) {
		LocalConfiguration.current.screenHeightDp.dp.roundToPx()
	}
	val menuHeight = remember {
		mutableIntStateOf(0)
	}

	Column(modifier = modifier
		.fillMaxWidth()
		.onGloballyPositioned {
			elementCoordinates = it.positionInRoot().y
			elementCoordinatesInWindow = it.positionInWindow().y
		}
	) {
		ExposedDropdownMenuBox(
			expanded = expanded,
			onExpandedChange = {
				if (enabled) {
					expanded = !expanded
				}
			},
		) {
			CompositionLocalProvider(
				LocalOverscrollConfiguration provides null
			) {
				Column(
					modifier = Modifier.animateContentSize(tween(durationMillis = 350))
				) {
					val context = LocalContext.current
					DropDownTextInputField(
						selectedValue = selectedValue,
						label = label,
						expanded = expanded,
						onExpandChange = { expanded = !expanded },
						onMenuClose = { expanded = false },
						enabled = enabled,
						modifier = Modifier
							.menuAnchor()
							.onGloballyPositioned {
								textFieldHeight = it.size.height.toDp(context)
							}
							.zIndex(1f)
							.onFocusChanged {
								expanded = it.hasFocus
							},
					)

					if (visibilitySupportingText && !expanded) {
						SupportingText(
							errorMessage = errorMessage,
							optional = optional,
							optionalMessage = optionalMessage,
						)
					}

					DropMenu(
						options = options,
						onValueSelect = onValueSelect,
						onExpandChange = { expanded = !expanded },
						onMenuHeightChange = { menuHeight.intValue = it },
						textFieldHeight = textFieldHeight,
						expanded = expanded,
					)
				}
			}
		}
	}

	LaunchedEffect(expanded) {
		if (expanded) {
			scrollState.animateScrollBy(
				value = getScrollValue(
					elementCoordinatesInWindow,
					heightScreenInPx,
					menuHeight.intValue,
					elementCoordinates,
				),
				animationSpec = tween(durationMillis = 450)
			)
		}
	}
}

private fun getScrollValue(
	elementCoordinatesWindow: Float,
	heightScreenInPx: Int,
	menuHeight: Int,
	elementCoordinates: Float,
) = if ((elementCoordinatesWindow + menuHeight) < heightScreenInPx.toFloat()) {
	min(0f, elementCoordinates)
} else {
	val paddingBottom = 16.dp
	elementCoordinatesWindow + menuHeight + paddingBottom.value - heightScreenInPx
}

@Composable
private fun DropDownTextInputField(
	label: String,
	expanded: Boolean,
	onExpandChange: () -> Unit,
	onMenuClose: () -> Unit,
	modifier: Modifier = Modifier,
	selectedValue: String = "",
	enabled: Boolean = false,
) {
	var focused by remember { mutableStateOf(selectedValue.isNotEmpty()) }

	var labelStyle by remember { mutableStateOf(selectLabelStyle(selectedValue, focused)) }

	val colors = TextFieldDefaults.colors(
		focusedTextColor = MaterialTheme.colorScheme.onSurface,
		unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
		disabledTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
		focusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
		unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
		disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
		focusedIndicatorColor = MaterialTheme.colorScheme.primaryContainer,
		unfocusedIndicatorColor = MaterialTheme.colorScheme.primaryContainer,
		disabledIndicatorColor = MaterialTheme.colorScheme.primaryContainer,
		focusedContainerColor = MaterialTheme.colorScheme.surface,
		unfocusedContainerColor = MaterialTheme.colorScheme.surface,
		disabledContainerColor = MaterialTheme.colorScheme.surfaceDim,
		errorContainerColor = MaterialTheme.colorScheme.surface,
		errorLabelColor = MaterialTheme.colorScheme.primary,
		errorIndicatorColor = MaterialTheme.colorScheme.primary,
	)

	Column(modifier = modifier.fillMaxWidth()) {
		OutlinedTextFieldBackground(
			enabledColor = MaterialTheme.colorScheme.surface,
			disabledColor = MaterialTheme.colorScheme.surfaceDim,
			enabled = enabled
		) {
			OutlinedTextField(
				value = selectedValue,
				onValueChange = {},
				textStyle = MaterialTheme.typography.bodyLarge,
				enabled = enabled,
				readOnly = true,
				colors = colors,
				shape = MaterialTheme.shapes.medium,
				trailingIcon = {
					Icon(
						painter = if (expanded) {
							painterResource(id = R.drawable.ic_arrow_up)
						} else {
							painterResource(id = R.drawable.ic_arrow_down)
						},
						contentDescription = null,
						tint = MaterialTheme.colorScheme.onSurface,
					)
				},
				label = {
					BaseText(
						text = label,
						style = labelStyle,
						modifier = Modifier.background(Color.Transparent),
					)
				},
				modifier = Modifier
					.fillMaxWidth()
					.background(
						shape = MaterialTheme.shapes.medium,
						color = Color.Transparent,
					)
					.applyIf(enabled) {
						clickable {
							onExpandChange()
						}
					}
					.onFocusChanged {
						focused = it.isFocused
					},
			)
		}

		LaunchedEffect(focused) {
			if (!focused) {
				onMenuClose()
			}
		}
		LaunchedEffect(selectedValue.isEmpty(), focused) {
			labelStyle = selectLabelStyle(selectedValue, focused)
		}
	}
}

private fun selectLabelStyle(
	text: String,
	focused: Boolean,
) = if (text.isNotEmpty() || focused) {
	Typography.bodyMedium
} else {
	Typography.bodyLarge
}

@Composable
private fun DropMenu(
	options: List<String>,
	textFieldHeight: Dp,
	expanded: Boolean,
	onValueSelect: (String) -> Unit,
	onExpandChange: () -> Unit,
	onMenuHeightChange: (Int) -> Unit,
) {
	val scrollState = rememberScrollState()
	val itemSize = 20.dp
	val paddingValues = 8.dp

	if (expanded) {
		Box(
			modifier = Modifier
				.heightIn(max = (textFieldHeight * 3) + itemSize)
				.offset(y = -itemSize)
				.withScrollbar(
					state = scrollState,
					itemsCount = options.size,
					knobColor = MaterialTheme.colorScheme.primaryContainer,
					visibleItemsCount = 4,
					paddingTop = itemSize + paddingValues,
					paddingBottom = itemSize + paddingValues,
				)
				.border(
					width = 2.dp,
					color = MaterialTheme.colorScheme.primaryContainer,
					shape = RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 20.dp),
				)
				.onGloballyPositioned {
					onMenuHeightChange(it.size.height)
				}
				.background(
					shape = MaterialTheme.shapes.medium,
					color = MaterialTheme.colorScheme.surface,
				)
		) {
			Column(modifier = Modifier.verticalScroll(scrollState)) {
				Box(modifier = Modifier.height((itemSize / 2) + paddingValues))

				options.forEach {
					DropDownOption(
						text = it,
						onValueSelect = { onValueSelect(it) },
						onExpandChange = onExpandChange,
					)
				}
			}
		}
	}
}

@Composable
private fun DropDownOption(
	text: String,
	onValueSelect: () -> Unit,
	onExpandChange: () -> Unit,
) {
	DropdownMenuItem(
		onClick = {
			onValueSelect()
			onExpandChange()
		},
		text = {
			BodyLargeText(text = text)
		},
		contentPadding = PaddingValues(vertical = 10.dp, horizontal = 22.dp),
	)
}

@Composable
fun LoadingDropDownText(
	paddingValues: PaddingValues = PaddingValues(top = 8.dp, bottom = 20.dp),
) {
	Box(
		modifier = Modifier
			.fillMaxWidth()
			.background(
				color = Color.Transparent,
			)
			.padding(paddingValues)
			.border(
				width = 1.dp,
				color = MaterialTheme.colorScheme.surfaceContainer,
				shape = MaterialTheme.shapes.medium
			),
	) {
		Row {
			BaseText(
				text = " ", style = Typography.bodyLarge,
				modifier = Modifier
					.padding(vertical = 18.dp)
					.padding(start = 16.dp)
					.shimmerAnimation()
					.width(200.dp),
			)

			Spacer(modifier = Modifier.weight(1f))

			Icon(
				painter = painterResource(id = R.drawable.ic_arrow_down),
				contentDescription = null,
				tint = MaterialTheme.colorScheme.onSurface,
				modifier = Modifier
					.align(CenterVertically)
					.padding(end = 16.dp),
			)
		}
	}
}

@Preview
@Composable
private fun DropdownTextPreview() {
	var selectedValue by remember { mutableStateOf("") }
	val scrollState = rememberScrollState()
	fun generateOptions(i: Int): List<String> {
		return List(i) { index -> "Опция $index" }
	}
	AppTheme {
		Screen {
			Column(
				modifier = Modifier
					.verticalScroll(scrollState)
					.padding(8.dp)
			) {
				DropdownText(
					options = listOf(
						"Опция 1",
						"Опция 2",
						"Опция 1",
						"Опция 2",
					),
					label = "Где узнали о нас",
					scrollState = scrollState,
					onValueSelect = { selectedValue = it },
					selectedValue = selectedValue,
					optional = true,
				)

				DropdownText(
					options = listOf(
						"Опция 1",
						"Опция 2",
						"Опция 1",
						"Опция 2",
					),
					label = "Название секции",
					scrollState = scrollState,
					onValueSelect = { selectedValue = it },
					selectedValue = selectedValue,
					enabled = false,
					optional = true,
					optionalMessage = "Администратор запретил изменять это поле"
				)

				LoadingDropDownText()

				for (i in 1..20) {
					var values by remember { mutableStateOf("") }
					DropdownText(
						options = generateOptions(i),
						enabled = i != 1,
						label = "Где узнали о нас $i",
						scrollState = scrollState,
						onValueSelect = { values = it },
						selectedValue = values,
					)
				}
			}
		}
	}
}