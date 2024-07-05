package ru.nikpanfilov.delivery.core.error

sealed class NetworkError {
	data object Unknown : NetworkError()

	data class ErrorMessage(
		val statusCode: Int,
		val message: List<String>,
	) : NetworkError()
}