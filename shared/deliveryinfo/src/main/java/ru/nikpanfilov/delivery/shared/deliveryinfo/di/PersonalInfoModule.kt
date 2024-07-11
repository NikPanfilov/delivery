package ru.nikpanfilov.delivery.shared.deliveryinfo.di

import android.content.Context
import com.squareup.moshi.Moshi
import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.nikpanfilov.delivery.shared.deliveryinfo.data.datasource.PersonalInfoDataSource
import ru.nikpanfilov.delivery.shared.deliveryinfo.data.datasource.PersonalInfoDataSourceImpl
import ru.nikpanfilov.delivery.shared.deliveryinfo.data.dto.PersonalInfoDto
import ru.nikpanfilov.delivery.shared.deliveryinfo.data.repository.PersonalInfoRepositoryImpl
import ru.nikpanfilov.delivery.shared.deliveryinfo.domain.repository.PersonalInfoRepository

@Module
interface PersonalInfoModule {

	companion object {

		@Provides
		fun providePersonalInfoDataSource(
			context: Context,
			moshi: Moshi,
		): PersonalInfoDataSource = PersonalInfoDataSourceImpl(
			sharedPreferences = context.getSharedPreferences(PersonalInfoDataSourceImpl.PERSONAL_INFO_PREFS, Context.MODE_PRIVATE),
			adapter = moshi.adapter(PersonalInfoDto::class.java),
		)
	}

	@Binds
	fun bindPersonalInfoRepository(personalInfoRepositoryImpl: PersonalInfoRepositoryImpl): PersonalInfoRepository
}