package ru.nikpanfilov.delivery.core.token.data.repository

import ru.nikpanfilov.delivery.core.token.data.datasource.LocalTokenDataSource
import ru.nikpanfilov.delivery.core.token.domain.repository.TokenRepository
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
	private val localDataSource: LocalTokenDataSource,
) : TokenRepository {

	override fun getToken(): String = localDataSource.getToken()

	override fun clearToken() {
		localDataSource.clearToken()
	}

	override fun isTokenExist(): Boolean =
		localDataSource.isTokenExist()
}