package ru.nikpanfilov.delivery.core.token.data.datasource

import android.content.SharedPreferences

class LocalTokenDataSourceImpl(
	private val sharedPreferences: SharedPreferences,
) : LocalTokenDataSource {

	companion object {

		const val TOKEN_SHARED_PREF = "TOKEN_SHARED_PREF"
		private const val TOKEN_KEY = "TOKEN_KEY"
	}

	override fun saveToken(token: String) {
		sharedPreferences.edit().putString(TOKEN_KEY, token).apply()
	}

	override fun getToken(): String =
		sharedPreferences.getString(TOKEN_KEY, "") ?: error("Token does not exist")

	override fun clearToken() {
		sharedPreferences.edit().remove(TOKEN_KEY).apply()
	}

	override fun isTokenExist(): Boolean =
		sharedPreferences.contains(TOKEN_KEY)
}