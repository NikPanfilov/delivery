package ru.nikpanfilov.delivery.core.token.domain.repository

interface TokenRepository {

	fun getToken(): String

	fun clearToken()

	fun isTokenExist(): Boolean
}