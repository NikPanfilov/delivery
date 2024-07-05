package ru.nikpanfilov.delivery.core.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat

private val lightColorScheme = lightColorScheme(
	primary = Primary,
	onPrimary = OnPrimary,
	primaryContainer = SurfaceContainer,
	onPrimaryContainer = OnSurfaceContainer,
	inversePrimary = InversePrimary,
	secondary = Secondary,
	onSecondary = OnSecondary,
	secondaryContainer = Ripple,
	onSecondaryContainer = OnRipple,
	error = Error,
	onError = OnError,
	surface = Surface,
	onSurface = OnSurface,
	surfaceContainer = SurfaceContainer,
	onSurfaceVariant = OnSurfaceVariant,
	background = Background,
	onBackground = OnBackground,
	outline = Outline,
	onTertiary = OnTertiary,
	outlineVariant = OutlineVariant,
	surfaceDim = surfaceDim,
	inverseOnSurface = inverseOnSurface,
)

private val darkColorScheme = darkColorScheme(
	primary = DarkPrimary,
	onPrimary = DarkOnPrimary,
	primaryContainer = DarkSurfaceContainer,
	onPrimaryContainer = DarkOnSurfaceContainer,
	inversePrimary = DarkInversePrimary,
	secondary = DarkSecondary,
	onSecondary = DarkOnSecondary,
	secondaryContainer = DarkRipple,
	onSecondaryContainer = DarkOnRipple,
	error = DarkError,
	onError = DarkOnError,
	surface = DarkSurface,
	onSurface = DarkOnSurface,
	surfaceContainer = SurfaceContainer,
	onSurfaceVariant = DarkOnSurfaceVariant,
	background = DarkBackground,
	onBackground = DarkOnBackground,
	outline = DarkOutline,
	onTertiary = DarkOnTertiary,
	surfaceDim = DarkSurfaceDim,
	inverseOnSurface = DarkInverseOnSurface,
)

private val shapes = Shapes(
	small = RoundedCornerShape(15.dp),
	medium = RoundedCornerShape(20.dp),
	large = RoundedCornerShape(28.dp),
)

@Composable
fun AppTheme(
	darkTheme: Boolean = isSystemInDarkTheme(),
	content: @Composable () -> Unit,
) {
	val colorScheme = if (darkTheme) {
		darkColorScheme
	} else {
		lightColorScheme
	}

	val view = LocalView.current
	if (!view.isInEditMode) {
		SideEffect {
			(view.context as? Activity)?.window?.apply {
				statusBarColor = colorScheme.background.toArgb()
				navigationBarColor = colorScheme.background.toArgb()
				WindowCompat.getInsetsController(this, view).apply {
					isAppearanceLightStatusBars = !darkTheme
					isAppearanceLightNavigationBars = !darkTheme
				}
			}
		}
	}

	MaterialTheme(
		colorScheme = colorScheme,
		typography = Typography,
		shapes = shapes,
		content = content,
	)
}