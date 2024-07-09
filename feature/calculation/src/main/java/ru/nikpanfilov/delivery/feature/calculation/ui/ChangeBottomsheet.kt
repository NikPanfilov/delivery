package ru.nikpanfilov.delivery.feature.calculation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.nikpanfilov.delivery.core.ui.compose.BodyLargeText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun <T : Any> ChangeBottomsheet(
	title: String,
	options: List<T>,
	onDismiss: () -> Unit,
	item: @Composable (T) -> Unit,
) {
	ModalBottomSheet(
		onDismissRequest = onDismiss,
		dragHandle = {},
	) {
		Column(modifier = Modifier.padding(start = 16.dp, top = 20.dp)) {
			BodyLargeText(
				text = title,
				modifier = Modifier.padding(vertical = 16.dp),
			)

			LazyColumn {
				items(
					items = options,
					key = { it.hashCode() },
				) {
					item(it)
				}
			}
		}
	}
}