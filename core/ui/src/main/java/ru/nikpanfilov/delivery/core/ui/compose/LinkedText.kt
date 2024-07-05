package ru.nikpanfilov.delivery.core.ui.compose

import android.text.SpannableString
import android.text.style.URLSpan
import android.text.util.Linkify
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.core.text.getSpans

@Composable
fun LinkedText(
	text: String,
	style: TextStyle,
	onOpenLink: (String) -> Unit,
	modifier: Modifier = Modifier,
	textColor: Color = Color.Unspecified,
	maxLines: Int = Int.MAX_VALUE,
	overflow: TextOverflow = TextOverflow.Clip,
	onTextLayout: ((TextLayoutResult) -> Unit)? = null,
) {
	val links = remember(text) {
		getLinksFromText(text)
	}

	val annotatedString = buildAnnotatedString {
		append(text)
		addStyle(
			style = SpanStyle(color = textColor),
			start = 0,
			end = text.length,
		)

		links.forEach {
			addStyle(
				style = SpanStyle(
					color = MaterialTheme.colorScheme.inverseOnSurface,
					textDecoration = TextDecoration.Underline,
				),
				start = it.start,
				end = it.end,
			)
			addStringAnnotation(
				tag = "url",
				annotation = it.url,
				start = it.start,
				end = it.end,
			)
		}
	}

	ClickableText(
		text = annotatedString,
		onClick = {
			links
				.find { link ->
					it in link.start..link.end
				}
				?.also {
					onOpenLink(it.url)
				}
		},
		style = style,
		modifier = modifier,
		maxLines = maxLines,
		overflow = overflow,
		onTextLayout = onTextLayout ?: {},
	)
}

private fun getLinksFromText(text: String): List<Link> {
	val spannableString = SpannableString(text)
	Linkify.addLinks(spannableString, Linkify.WEB_URLS)

	return spannableString.getSpans<URLSpan>().map {
		Link(
			url = it.url,
			start = spannableString.getSpanStart(it),
			end = spannableString.getSpanEnd(it),
		)
	}
}

private data class Link(
	val url: String,
	val start: Int,
	val end: Int
)