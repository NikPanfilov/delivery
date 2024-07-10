package ru.nikpanfilov.delivery.core.error

import com.squareup.moshi.Moshi
import retrofit2.HttpException
import ru.nikpanfilov.delivery.core.error.data.dto.ErrorMessageDto
import ru.nikpanfilov.delivery.core.error.data.mapper.toEntity

private val moshi: Moshi = Moshi.Builder().build()
private val jsonAdapter = moshi.adapter(ErrorMessageDto::class.java)

internal fun Exception.getNetworkErrorMessage(): NetworkError =
	when (this) {
		is HttpException -> getHttpErrorMessage()
		else             -> NetworkError.Unknown
	}

private fun HttpException.getHttpErrorMessage(): NetworkError =
	this.response()?.errorBody()?.string()
		.takeIf { !it.isNullOrBlank() }
		?.let { jsonAdapter.fromJson(it)?.toEntity() }
		?: getErrorWithoutBody(code())

private fun getErrorWithoutBody(statusCode: Int): NetworkError.ErrorMessage =
	NetworkError.ErrorMessage(
		statusCode = statusCode,
		message = "",
	)
