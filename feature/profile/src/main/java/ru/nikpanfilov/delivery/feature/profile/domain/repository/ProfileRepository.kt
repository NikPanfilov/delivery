package ru.nikpanfilov.delivery.feature.profile.domain.repository

import ru.nikpanfilov.delivery.shared.user.domain.entity.User

interface ProfileRepository {

	suspend fun update(user: User)
}