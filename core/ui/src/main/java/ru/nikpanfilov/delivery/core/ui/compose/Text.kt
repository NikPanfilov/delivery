package ru.nikpanfilov.delivery.core.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TitleLargeText(
	text: String,
	modifier: Modifier = Modifier,
	textAlign: TextAlign? = null,
	color: Color = Color.Unspecified,
	maxLines: Int = Int.MAX_VALUE,
	overflow: TextOverflow = TextOverflow.Clip,
) {
	Text(
		text = text,
		style = MaterialTheme.typography.titleLarge,
		modifier = modifier,
		color = color,
		textAlign = textAlign,
		maxLines = maxLines,
		overflow = overflow,
	)
}

@Composable
fun TitleMediumText(
	text: String,
	modifier: Modifier = Modifier,
	textAlign: TextAlign? = null,
	color: Color = Color.Unspecified,
	maxLines: Int = Int.MAX_VALUE,
	overflow: TextOverflow = TextOverflow.Clip,
) {
	Text(
		text = text,
		style = MaterialTheme.typography.titleMedium,
		modifier = modifier,
		color = color,
		textAlign = textAlign,
		maxLines = maxLines,
		overflow = overflow,
	)
}

@Composable
fun TitleSmallText(
	text: String,
	modifier: Modifier = Modifier,
	textAlign: TextAlign? = null,
	color: Color = Color.Unspecified,
	maxLines: Int = Int.MAX_VALUE,
	overflow: TextOverflow = TextOverflow.Clip,
) {
	Text(
		text = text,
		style = MaterialTheme.typography.titleSmall,
		modifier = modifier,
		color = color,
		textAlign = textAlign,
		maxLines = maxLines,
		overflow = overflow,
	)
}

@Composable
fun BodyLargeText(
	text: String,
	modifier: Modifier = Modifier,
	textAlign: TextAlign? = null,
	color: Color = Color.Unspecified,
	maxLines: Int = Int.MAX_VALUE,
	overflow: TextOverflow = TextOverflow.Clip,
) {
	Text(
		text = text,
		style = MaterialTheme.typography.bodyLarge,
		modifier = modifier,
		color = color,
		textAlign = textAlign,
		maxLines = maxLines,
		overflow = overflow,
	)
}

@Composable
fun BodyMediumText(
	text: String,
	modifier: Modifier = Modifier,
	textAlign: TextAlign? = null,
	color: Color = Color.Unspecified,
	maxLines: Int = Int.MAX_VALUE,
	overflow: TextOverflow = TextOverflow.Clip,
) {
	Text(
		text = text,
		style = MaterialTheme.typography.bodyMedium,
		modifier = modifier,
		color = color,
		textAlign = textAlign,
		maxLines = maxLines,
		overflow = overflow,
	)
}

@Composable
fun BodySmallText(
	text: String,
	modifier: Modifier = Modifier,
	textAlign: TextAlign? = null,
	color: Color = Color.Unspecified,
	maxLines: Int = Int.MAX_VALUE,
	overflow: TextOverflow = TextOverflow.Clip,
) {
	Text(
		text = text,
		style = MaterialTheme.typography.bodySmall,
		modifier = modifier,
		color = color,
		textAlign = textAlign,
		maxLines = maxLines,
		overflow = overflow,
	)
}

@Composable
fun LabelLargeText(
	text: String,
	modifier: Modifier = Modifier,
	textAlign: TextAlign? = null,
	color: Color = Color.Unspecified,
	maxLines: Int = Int.MAX_VALUE,
	overflow: TextOverflow = TextOverflow.Clip,
) {
	Text(
		text = text,
		style = MaterialTheme.typography.labelLarge,
		modifier = modifier,
		color = color,
		textAlign = textAlign,
		maxLines = maxLines,
		overflow = overflow,
	)
}

@Composable
fun LabelMediumText(
	text: String,
	modifier: Modifier = Modifier,
	textAlign: TextAlign? = null,
	color: Color = Color.Unspecified,
	maxLines: Int = Int.MAX_VALUE,
	overflow: TextOverflow = TextOverflow.Clip,
) {
	Text(
		text = text,
		style = MaterialTheme.typography.labelMedium,
		modifier = modifier,
		color = color,
		textAlign = textAlign,
		maxLines = maxLines,
		overflow = overflow,
	)
}

@Composable
fun LabelSmallText(
	text: String,
	modifier: Modifier = Modifier,
	textAlign: TextAlign? = null,
	color: Color = Color.Unspecified,
	maxLines: Int = Int.MAX_VALUE,
	overflow: TextOverflow = TextOverflow.Clip,
) {
	Text(
		text = text,
		style = MaterialTheme.typography.labelSmall,
		modifier = modifier,
		color = color,
		textAlign = textAlign,
		maxLines = maxLines,
		overflow = overflow,
	)
}

@Composable
fun BaseText(
	text: String,
	style: TextStyle,
	modifier: Modifier = Modifier,
	textAlign: TextAlign? = null,
	textScale: Double = 1.0,
	color: Color = Color.Unspecified,
	maxLines: Int = Int.MAX_VALUE,
	overflow: TextOverflow = TextOverflow.Clip,
	softWrap: Boolean = true,
	onTextLayout: ((TextLayoutResult) -> Unit)? = null,
) {
	Text(
		text = text,
		modifier = modifier,
		color = color,
		textAlign = textAlign,
		maxLines = maxLines,
		overflow = overflow,
		softWrap = softWrap,
		onTextLayout = onTextLayout,
		style = style.copy(fontSize = style.fontSize * textScale),
	)
}

@Composable
fun ErrorText(
	text: String,
	modifier: Modifier = Modifier,
	textAlign: TextAlign? = null,
	maxLines: Int = Int.MAX_VALUE,
	overflow: TextOverflow = TextOverflow.Clip,
) {
	Text(
		text = text,
		modifier = modifier,
		style = MaterialTheme.typography.bodyMedium,
		color = MaterialTheme.colorScheme.error,
		textAlign = textAlign,
		maxLines = maxLines,
		overflow = overflow,
	)
}

@Composable
fun HelperText(
	text: String,
	modifier: Modifier = Modifier,
	textAlign: TextAlign? = null,
	maxLines: Int = Int.MAX_VALUE,
	overflow: TextOverflow = TextOverflow.Clip,
) {
	Text(
		text = text,
		modifier = modifier,
		style = MaterialTheme.typography.bodySmall,
		color = MaterialTheme.colorScheme.onSurfaceVariant,
		textAlign = textAlign,
		maxLines = maxLines,
		overflow = overflow,
	)
}

@Preview
@Composable
private fun Preview() {
	Screen {
		Column {
			TitleLargeText(text = "TitleLargeText")
			TitleMediumText(text = "TitleMediumText")
			TitleSmallText(text = "TitleSmallText")
			BodyLargeText(text = "BodyLargeText")
			BodyMediumText(text = "BodyMediumText")
			BodySmallText(text = "BodySmallText")
			LabelLargeText(text = "LabelLargeText")
			LabelMediumText(text = "LabelMediumText")
			LabelSmallText(text = "LabelSmallText")
			ErrorText(text = "ErrorText")
			HelperText(text = "HelperText")
		}
	}
}