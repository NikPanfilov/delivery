package ru.nikpanfilov.delivery.core.ui.compose

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.nikpanfilov.delivery.core.ui.R
import ru.nikpanfilov.delivery.core.ui.theme.AppTheme

/**
 * Компонент в фигме:
 * [Figma](https://www.figma.com/file/jBaZDfTxkix3FZ2iNC0oiQ/%D0%9C%D0%BE%D0%B1%D0%B8%D0%BB%D1%8C%D0%BD%D0%BE%D0%B5-%D0%BF%D1%80%D0%B8%D0%BB%D0%BE%D0%B6%D0%B5%D0%BD%D0%B8%D0%B5-%D0%A8%D0%98%D0%A4%D0%A2-%D0%98%D0%BD%D1%82%D0%B5%D0%BD%D1%81%D0%B8%D0%B2?type=design&node-id=993-2453&mode=design&t=9UvnntUkRpTwdw9r-4)
 */
@Composable
fun Dialog(
	title: String,
	description: String,
	confirmButtonText: String,
	onConfirm: () -> Unit,
	dismissButtonText: String = stringResource(id = R.string.cancel),
	onDismiss: (() -> Unit)? = null,
) {
	AlertDialog(
		onDismissRequest = onDismiss ?: onConfirm,
		title = {
			TitleLargeText(
				text = title,
				color = MaterialTheme.colorScheme.error,
			)
		},
		text = {
			BodyMediumText(
				text = description,
				color = MaterialTheme.colorScheme.onSurface,
			)
		},
		confirmButton = {
			TextButton(
				onClick = { onConfirm() },
				colors = ButtonDefaults.buttonColors(
					contentColor = MaterialTheme.colorScheme.error,
					containerColor = MaterialTheme.colorScheme.surface,
				),
			) {
				LabelMediumText(text = confirmButtonText)
			}
		},
		dismissButton = {
			if (onDismiss != null) {
				TextButton(
					onClick = { onDismiss() },
					colors = ButtonDefaults.buttonColors(
						contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
						containerColor = MaterialTheme.colorScheme.surface,
					),
				) {
					LabelMediumText(text = dismissButtonText)
				}

			}
		},
		containerColor = MaterialTheme.colorScheme.surface,
		tonalElevation = 0.dp,
	)
}

@Preview
@Composable
private fun TwoButtonDialogPreview() {
	AppTheme {
		Dialog(
			title = "Предупреждение",
			description = "Для продолжения необходимо удалить приложение.",
			confirmButtonText = "Зарегистрироватья",
			onConfirm = { },
			onDismiss = { },
		)
	}
}

@Preview
@Composable
private fun OneButtonDialogPreview() {
	AppTheme {
		Dialog(
			title = "Предупреждение",
			description = "Для продолжения необходимо дождаться подтверждения администратора. Попробуйте позже.",
			confirmButtonText = "Ок",
			onConfirm = { },
		)
	}
}
