package ru.nikpanfilov.delivery.core.ui.ext

import android.content.Context
import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.roundToInt

fun Int.toDp(context: Context): Dp =
	(this / context.resources.displayMetrics.density).roundToInt().dp

fun Date.format(patten: String): String {
	val format = SimpleDateFormat(patten, Locale.getDefault())
	return format.format(this).toString()
}

fun ByteArray.toImageBitmap(): ImageBitmap =
	BitmapFactory.decodeByteArray(this, 0, this.size).asImageBitmap()