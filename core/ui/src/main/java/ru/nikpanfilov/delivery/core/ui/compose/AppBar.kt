package ru.nikpanfilov.delivery.core.ui.compose

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.sharp.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.nikpanfilov.delivery.core.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
	title: String,
	leftIcon: ImageVector? = null,
	rightIcon: ImageVector? = null,
	onLeftButtonClick: (() -> Unit)? = null,
	onRightButtonClick: (() -> Unit)? = null,
) {
	key(MaterialTheme.colorScheme.background) {
		TopAppBar(
			title = {
				TitleMediumText(text = title)
			},
			modifier = Modifier
				.fillMaxWidth()
				.shadow(4.dp),
			colors = TopAppBarDefaults.topAppBarColors(
				containerColor = MaterialTheme.colorScheme.background,
				titleContentColor = MaterialTheme.colorScheme.onBackground,
				actionIconContentColor = MaterialTheme.colorScheme.primaryContainer,
				navigationIconContentColor = MaterialTheme.colorScheme.onBackground,
			),
			navigationIcon = {
				leftIcon?.let { icon ->
					IconButton(
						onClick = { onLeftButtonClick?.invoke() },
					) {
						Icon(
							imageVector = icon,
							contentDescription = null
						)
					}
				}
			},
			actions = {
				rightIcon?.let { icon ->
					IconButton(
						onClick = { onRightButtonClick?.invoke() },
					) {
						Icon(
							imageVector = icon,
							contentDescription = null
						)
					}
				}
			},
		)
	}
}

@Preview()
@Composable
private fun AppBarPreview() {
	AppTheme {
		Surface(
			color = MaterialTheme.colorScheme.background,
			modifier = Modifier.fillMaxSize()
		) {

		}
		AppBar(
			title = "App",
			leftIcon = Icons.Sharp.Done,
			rightIcon = Icons.Filled.ExitToApp,
			onLeftButtonClick = {
				Log.d("appBar", "Left button clicked")
			},
			onRightButtonClick = {
				Log.d("appBar", "Right button clicked")
			}
		)
	}
}