package ru.nikpanfilov.delivery.core.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ru.nikpanfilov.delivery.core.ui.R

internal val robotoFontFamily = FontFamily(
	Font(R.font.roboto_light, FontWeight(300), FontStyle.Normal),
	Font(R.font.roboto_regular, FontWeight(400), FontStyle.Normal),
	Font(R.font.roboto_italic, FontWeight(400), FontStyle.Italic),
	Font(R.font.roboto_medium, FontWeight(500), FontStyle.Normal),
	Font(R.font.roboto_medium_italic, FontWeight(500), FontStyle.Italic),
	Font(R.font.roboto_semibold, FontWeight(600), FontStyle.Normal),
	Font(R.font.roboto_semibold_italic, FontWeight(600), FontStyle.Italic),
	Font(R.font.roboto_bold, FontWeight(700), FontStyle.Normal),
)

internal val Typography = Typography(
	titleLarge = TextStyle(
		fontFamily = robotoFontFamily,
		fontSize = 24.sp,
		lineHeight = 32.sp,
		letterSpacing = 0.sp,
		fontStyle = FontStyle.Normal,
		fontWeight = FontWeight(700),
		platformStyle = PlatformTextStyle(
			includeFontPadding = false,
		),
	),
	titleMedium = TextStyle(
		fontFamily = robotoFontFamily,
		fontSize = 20.sp,
		lineHeight = 16.sp,
		letterSpacing = 0.sp,
		fontStyle = FontStyle.Normal,
		fontWeight = FontWeight(600),
		platformStyle = PlatformTextStyle(
			includeFontPadding = false,
		),
	),
	titleSmall = TextStyle(
		fontFamily = robotoFontFamily,
		fontSize = 20.sp,
		lineHeight = 24.sp,
		letterSpacing = 0.sp,
		fontStyle = FontStyle.Normal,
		fontWeight = FontWeight(600),
		platformStyle = PlatformTextStyle(
			includeFontPadding = false,
		),
	),
	bodyLarge = TextStyle(
		fontFamily = robotoFontFamily,
		fontSize = 16.sp,
		lineHeight = 24.sp,
		letterSpacing = 0.25.sp,
		fontStyle = FontStyle.Normal,
		fontWeight = FontWeight(500),
		platformStyle = PlatformTextStyle(
			includeFontPadding = false,
		),
	),
	bodyMedium = TextStyle(
		fontFamily = robotoFontFamily,
		fontSize = 14.sp,
		lineHeight = 18.sp,
		letterSpacing = 0.sp,
		fontStyle = FontStyle.Normal,
		fontWeight = FontWeight(500),
		platformStyle = PlatformTextStyle(
			includeFontPadding = false,
		),
	),
	bodySmall = TextStyle(
		fontFamily = robotoFontFamily,
		fontSize = 12.sp,
		lineHeight = 24.sp,
		letterSpacing = 0.sp,
		fontStyle = FontStyle.Normal,
		fontWeight = FontWeight(400),
		platformStyle = PlatformTextStyle(
			includeFontPadding = false,
		),
	),
	labelLarge = TextStyle(
		fontFamily = robotoFontFamily,
		fontSize = 20.sp,
		lineHeight = 36.sp,
		letterSpacing = 0.sp,
		fontStyle = FontStyle.Normal,
		fontWeight = FontWeight(600),
		platformStyle = PlatformTextStyle(
			includeFontPadding = false,
		),
	),
	labelMedium = TextStyle(
		fontFamily = robotoFontFamily,
		fontSize = 14.sp,
		lineHeight = 20.sp,
		letterSpacing = 0.sp,
		fontStyle = FontStyle.Normal,
		fontWeight = FontWeight(600),
		platformStyle = PlatformTextStyle(
			includeFontPadding = false,
		),
	),
	labelSmall = TextStyle(
		fontFamily = robotoFontFamily,
		fontSize = 11.sp,
		lineHeight = 14.sp,
		letterSpacing = 0.sp,
		fontStyle = FontStyle.Normal,
		fontWeight = FontWeight(500),
		platformStyle = PlatformTextStyle(
			includeFontPadding = false,
		),
	),
)

enum class ItalicStyle(val style: TextStyle) {
	ItalicSmall(
		TextStyle(
			fontFamily = robotoFontFamily,
			fontSize = 11.sp,
			lineHeight = 20.sp,
			letterSpacing = 0.sp,
			fontStyle = FontStyle.Italic,
			fontWeight = FontWeight(400),
			platformStyle = PlatformTextStyle(
				includeFontPadding = false,
			),
		)
	),
}

val ReferenceStyle = TextStyle(
	fontFamily = robotoFontFamily,
	fontSize = 13.sp,
	lineHeight = 20.sp,
	letterSpacing = 0.25.sp,
	fontStyle = FontStyle.Italic,
	fontWeight = FontWeight(500),
	platformStyle = PlatformTextStyle(
		includeFontPadding = false,
	),
)
