package ru.nikpanfilov.delivery.shared.deliveryinfo.di

import android.content.Context
import com.squareup.moshi.Moshi
import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.nikpanfilov.delivery.shared.deliveryinfo.data.datasource.DeliveryOptionDataSource
import ru.nikpanfilov.delivery.shared.deliveryinfo.data.datasource.DeliveryOptionDataSourceImpl
import ru.nikpanfilov.delivery.shared.deliveryinfo.data.dto.DeliveryOptionDto
import ru.nikpanfilov.delivery.shared.deliveryinfo.data.repository.DeliveryOptionRepositoryImpl
import ru.nikpanfilov.delivery.shared.deliveryinfo.domain.repository.DeliveryOptionRepository

@Module
interface DeliveryOptionModule {

	companion object {

		@Provides
		fun provideSaveOptionDataSource(
			context: Context,
			moshi: Moshi,
		): DeliveryOptionDataSource = DeliveryOptionDataSourceImpl(
			sharedPreferences = context.getSharedPreferences(DeliveryOptionDataSourceImpl.DELIVERY_OPTIONS_PREFS, Context.MODE_PRIVATE),
			adapter = moshi.adapter(DeliveryOptionDto::class.java),
		)
	}

	@Binds
	fun bindDeliveryOptionRepository(deliveryOptionRepositoryImpl: DeliveryOptionRepositoryImpl): DeliveryOptionRepository
}