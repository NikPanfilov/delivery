package ru.nikpanfilov.delivery.core.error.presentation

import kotlinx.coroutines.CoroutineExceptionHandler
import ru.nikpanfilov.delivery.core.error.NetworkError
import ru.nikpanfilov.delivery.core.error.getNetworkErrorMessage
import javax.inject.Inject

interface ErrorDelegate {

	fun handleError(networkError: NetworkError): HandleErrorResult
}

class ErrorDelegateImpl @Inject constructor() : ErrorDelegate {

	override fun handleError(networkError: NetworkError): HandleErrorResult =
		when (networkError) {
			is NetworkError.ErrorMessage -> {
				//TODO(Обработка общих ошибок)
				HandleErrorResult.NOT_HANDLED
			}

			is NetworkError.Unknown      -> {
				HandleErrorResult.NOT_HANDLED
			}
		}
}

fun ErrorDelegate.asCoroutineExceptionHandler(lambda: (NetworkError) -> Unit): CoroutineExceptionHandler =
	CoroutineExceptionHandler { _, throwable ->
		if (throwable is Exception) {
			val error = throwable.getNetworkErrorMessage()
			if (handleError(error) == HandleErrorResult.NOT_HANDLED) {
				lambda(error)
			}
		}
	}