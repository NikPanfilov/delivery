package ru.nikpanfilov.delivery.shared.deliveryinfo.di

import android.content.Context
import com.squareup.moshi.Moshi
import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.nikpanfilov.delivery.shared.deliveryinfo.data.datasource.AddressDataSource
import ru.nikpanfilov.delivery.shared.deliveryinfo.data.datasource.AddressDataSourceImpl
import ru.nikpanfilov.delivery.shared.deliveryinfo.data.dto.AddressDto
import ru.nikpanfilov.delivery.shared.deliveryinfo.data.repository.AddressRepositoryImpl
import ru.nikpanfilov.delivery.shared.deliveryinfo.domain.repository.AddressRepository

@Module
interface AddressModule {

	companion object {

		@Provides
		fun provideAddressDataSource(
			context: Context,
			moshi: Moshi,
		): AddressDataSource = AddressDataSourceImpl(
			sharedPreferences = context.getSharedPreferences(AddressDataSourceImpl.ADDRESS_PREFS, Context.MODE_PRIVATE),
			adapter = moshi.adapter(AddressDto::class.java),
		)
	}

	@Binds
	fun bindAddressRepository(addressRepositoryImpl: AddressRepositoryImpl): AddressRepository
}