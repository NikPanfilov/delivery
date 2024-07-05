package ru.nikpanfilov.delivery.core.token.di

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.nikpanfilov.delivery.core.token.data.datasource.LocalTokenDataSource
import ru.nikpanfilov.delivery.core.token.data.datasource.LocalTokenDataSourceImpl
import ru.nikpanfilov.delivery.core.token.data.repository.TokenRepositoryImpl
import ru.nikpanfilov.delivery.core.token.domain.repository.TokenRepository

@Module(includes = [TokenBindsModule::class])
object TokenModule {

	@Provides
	fun provideDataSource(
		context: Context,
	): LocalTokenDataSource = LocalTokenDataSourceImpl(context.getSharedPreferences(LocalTokenDataSourceImpl.TOKEN_SHARED_PREF, Context.MODE_PRIVATE))
}

@Module
interface TokenBindsModule {

	@Binds
	fun bindTokenRepository(repositoryImpl: TokenRepositoryImpl): TokenRepository
}