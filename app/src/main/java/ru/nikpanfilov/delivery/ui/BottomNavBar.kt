package ru.nikpanfilov.delivery.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.nikpanfilov.delivery.R
import ru.nikpanfilov.delivery.core.navigation.Destination
import ru.nikpanfilov.delivery.core.ui.compose.LabelSmallText
import ru.nikpanfilov.delivery.feature.calculation.presentation.CalculationDestination

data class NavigationItem(
	val title: String,
	val destination: Destination,
	@DrawableRes val selectedIcon: Int,
	@DrawableRes val unselectedIcon: Int,
	val onClick: () -> Unit,
)

@Composable
internal fun NavBar(
	currentScreen: Destination?,
	onOpenCalculation: () -> Unit,
	onOpenHistory: () -> Unit,
	onOpenOpenProfile: () -> Unit,
	modifier: Modifier = Modifier,
) {
	val context = LocalContext.current
	val navigationItemsList = remember {
		listOf(
			NavigationItem(
				title = context.getString(R.string.calculation_section),
				destination = CalculationDestination,
				selectedIcon = R.drawable.ic_calc_filled,
				unselectedIcon = R.drawable.ic_calc,
				onClick = onOpenCalculation,
			),
			NavigationItem(
				title = context.getString(R.string.profile_section),
				destination = CalculationDestination,//TODO(Заменить на ProfileDestination когда оно будет)
				selectedIcon = R.drawable.ic_profile_filled,
				unselectedIcon = R.drawable.ic_profile,
				onClick = onOpenOpenProfile,
			),
		)
	}

	NavigationBar(
		containerColor = MaterialTheme.colorScheme.background,
		contentColor = MaterialTheme.colorScheme.primary,
		modifier = modifier,
	) {
		navigationItemsList.forEach {
			NavigationBarItem(
				selected = currentScreen == it.destination,
				label = {
					LabelSmallText(text = it.title)
				},
				onClick = it.onClick,
				colors = NavigationBarItemDefaults.colors(
					selectedIconColor = MaterialTheme.colorScheme.primary,
					selectedTextColor = MaterialTheme.colorScheme.primary,
					unselectedIconColor = MaterialTheme.colorScheme.outline,
					unselectedTextColor = MaterialTheme.colorScheme.outline,
					indicatorColor = MaterialTheme.colorScheme.background,
				),
				alwaysShowLabel = true,
				icon = {
					Icon(
						painter = painterResource(
							if (currentScreen == it.destination) {
								it.selectedIcon
							} else {
								it.unselectedIcon
							}
						),
						tint = Color.Unspecified,
						contentDescription = null,
						modifier = Modifier.size(28.dp),
					)
				}
			)
		}
	}
}