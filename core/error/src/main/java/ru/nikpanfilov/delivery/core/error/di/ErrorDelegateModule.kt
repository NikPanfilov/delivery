package ru.nikpanfilov.delivery.core.error.di

import dagger.Binds
import dagger.Module
import ru.nikpanfilov.delivery.core.error.presentation.ErrorDelegate
import ru.nikpanfilov.delivery.core.error.presentation.ErrorDelegateImpl

@Module
interface ErrorModule {

	@Binds
	fun bindErrorDelegate(errorDelegateImpl: ErrorDelegateImpl): ErrorDelegate
}