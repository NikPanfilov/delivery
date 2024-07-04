package ru.nikpanfilov.delivery.core.ui.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.nikpanfilov.delivery.core.ui.R

@Composable
fun Error(
	onTryAgain: () -> Unit,
	modifier: Modifier = Modifier,
) {
	Column(
		modifier = modifier
			.padding(horizontal = 38.dp)
			.fillMaxSize(),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.Center,
	) {
		BodyMediumText(
			text = stringResource(id = R.string.error_title),
			textAlign = TextAlign.Center,
		)

		Spacer(modifier = Modifier.height(4.dp))

		BodySmallText(
			text = stringResource(id = R.string.error_text),
			textAlign = TextAlign.Center,
			color = MaterialTheme.colorScheme.onSurfaceVariant,
		)

		Spacer(modifier = Modifier.height(20.dp))

		CustomButton(
			text = stringResource(id = R.string.error_button),
			onClick = onTryAgain,
		)
	}
}

@Preview
@Composable
private fun Preview() {
	Screen {
		Error({})
	}
}