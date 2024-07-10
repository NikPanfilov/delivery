package ru.nikpanfilov.delivery.feature.profile.data.repository

import ru.nikpanfilov.delivery.feature.profile.data.api.ProfileApi
import ru.nikpanfilov.delivery.feature.profile.data.mapper.toUpdateDto
import ru.nikpanfilov.delivery.feature.profile.domain.repository.ProfileRepository
import ru.nikpanfilov.delivery.shared.user.domain.entity.User
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
	private val api: ProfileApi,
) : ProfileRepository {

	override suspend fun update(user: User) = api.update(user.toUpdateDto())
}