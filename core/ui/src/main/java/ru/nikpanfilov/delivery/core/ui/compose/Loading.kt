package ru.nikpanfilov.delivery.core.ui.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Loading(modifier: Modifier = Modifier) {
	CircularProgressIndicator(
		modifier = modifier.fillMaxSize(),
	)
}

@Preview
@Composable
private fun Preview() {
	Screen {
		Loading()
	}
}