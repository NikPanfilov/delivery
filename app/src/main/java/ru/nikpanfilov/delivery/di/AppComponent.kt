package ru.nikpanfilov.delivery.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.nikpanfilov.delivery.MainActivity
import ru.nikpanfilov.delivery.core.error.di.ErrorModule
import ru.nikpanfilov.delivery.core.navigation.GlobalRouter
import ru.nikpanfilov.delivery.core.navigation.NavControllerHolder
import ru.nikpanfilov.delivery.core.network.di.NetworkModule
import ru.nikpanfilov.delivery.core.token.di.TokenModule
import ru.nikpanfilov.delivery.feature.calculation.di.CalculationModule
import ru.nikpanfilov.delivery.feature.calculation.presentation.CalculationViewModel
import ru.nikpanfilov.delivery.feature.personalinfo.presentation.PersonalInfoViewModel
import ru.nikpanfilov.delivery.feature.profile.di.ProfileModule
import ru.nikpanfilov.delivery.feature.profile.presentation.ProfileViewModel
import ru.nikpanfilov.delivery.feature.shippingmethod.presentation.ShippingMethodViewModel
import ru.nikpanfilov.delivery.feature.signin.di.SignInModule
import ru.nikpanfilov.delivery.feature.signin.presentation.SignInViewModel
import ru.nikpanfilov.delivery.presentation.MainViewModel
import ru.nikpanfilov.delivery.shared.cities.di.CitiesModule
import ru.nikpanfilov.delivery.shared.deliveryinfo.di.AddressModule
import ru.nikpanfilov.delivery.shared.deliveryinfo.di.DeliveryOptionModule
import ru.nikpanfilov.delivery.shared.deliveryinfo.di.PersonalInfoModule
import ru.nikpanfilov.delivery.shared.user.di.UserModule
import javax.inject.Singleton

@Component(
	modules = [
		RoutersModule::class,
		TokenModule::class,
		NetworkModule::class,
		ErrorModule::class,
		CitiesModule::class,
		CalculationModule::class,
		SignInModule::class,
		UserModule::class,
		ProfileModule::class,
		DeliveryOptionModule::class,
		PersonalInfoModule::class,
		AddressModule::class,
	]
)
@Singleton
interface AppComponent {

	@Component.Builder
	interface Builder {

		@BindsInstance
		fun context(context: Context): Builder

		fun build(): AppComponent
	}

	fun inject(activity: MainActivity)

	val globalRouter: GlobalRouter

	val navControllerHolder: NavControllerHolder

	val mainViewModel: MainViewModel.Factory
	val calculationViewModel: CalculationViewModel.Factory
	val signInViewModel: SignInViewModel.Factory
	val profileViewModel: ProfileViewModel.Factory
	val shippingMethodViewModel: ShippingMethodViewModel.Factory
	val personalInfoViewModel: PersonalInfoViewModel.Factory
}
