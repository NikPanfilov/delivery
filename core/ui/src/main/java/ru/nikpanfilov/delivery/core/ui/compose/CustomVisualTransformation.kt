package ru.nikpanfilov.delivery.core.ui.compose

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class CustomVisualTransformation(private val mask: String, private val maskNumber: Char) : VisualTransformation {

	private val maxLength = mask.count { it == maskNumber }

	override fun filter(text: AnnotatedString): TransformedText {
		val trimmed = text.take(maxLength)

		val annotatedString = buildAnnotatedString {
			if (trimmed.isEmpty()) return@buildAnnotatedString

			var maskIndex = 0
			var textIndex = 0
			while (textIndex < trimmed.length && maskIndex < mask.length) {
				if (mask[maskIndex] != maskNumber) {
					val nextDigitIndex = mask.indexOf(maskNumber, maskIndex)
					append(mask.substring(maskIndex, nextDigitIndex))
					maskIndex = nextDigitIndex
				}
				append(trimmed[textIndex++])
				maskIndex++
			}
		}

		return TransformedText(annotatedString, CustomOffsetMapper(mask, maskNumber))
	}
}

private class CustomOffsetMapper(val mask: String, val numberChar: Char) : OffsetMapping {

	override fun originalToTransformed(offset: Int): Int {
		var noneDigitCount = 0
		var i = 0
		while (i < offset + noneDigitCount) {
			if (mask[i++] != numberChar) noneDigitCount++
		}
		return offset + noneDigitCount
	}

	override fun transformedToOriginal(offset: Int): Int =
		offset - mask.take(offset).count { it != numberChar }
}