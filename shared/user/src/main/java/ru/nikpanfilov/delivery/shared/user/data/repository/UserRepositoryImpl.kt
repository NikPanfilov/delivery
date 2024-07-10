package ru.nikpanfilov.delivery.shared.user.data.repository

import ru.nikpanfilov.delivery.shared.user.data.api.UserApi
import ru.nikpanfilov.delivery.shared.user.data.mapper.toEntity
import ru.nikpanfilov.delivery.shared.user.domain.entity.User
import ru.nikpanfilov.delivery.shared.user.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
	private val api: UserApi,
) : UserRepository {

	override suspend fun get(): User = api.get().user.toEntity()
}