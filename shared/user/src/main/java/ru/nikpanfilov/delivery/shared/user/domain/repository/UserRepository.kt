package ru.nikpanfilov.delivery.shared.user.domain.repository

import ru.nikpanfilov.delivery.shared.user.domain.entity.User

interface UserRepository {

	suspend fun get(): User
}