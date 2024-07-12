package ru.nikpanfilov.delivery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.json.Json
import ru.nikpanfilov.delivery.core.navigation.NavControllerHolder
import ru.nikpanfilov.delivery.core.presentation.lazyViewModel
import ru.nikpanfilov.delivery.core.ui.compose.Screen
import ru.nikpanfilov.delivery.feature.addressinfo.AddressInfoDestination
import ru.nikpanfilov.delivery.feature.addressinfo.ui.AddressInfoScreen
import ru.nikpanfilov.delivery.feature.calculation.presentation.CalculationDestination
import ru.nikpanfilov.delivery.feature.calculation.ui.CalculationScreen
import ru.nikpanfilov.delivery.feature.personalinfo.PersonalInfoDestination
import ru.nikpanfilov.delivery.feature.personalinfo.ui.PersonalInfoScreen
import ru.nikpanfilov.delivery.feature.profile.ProfileDestination
import ru.nikpanfilov.delivery.feature.profile.ui.ProfileScreen
import ru.nikpanfilov.delivery.feature.shippingmethod.ShippingMethodDestination
import ru.nikpanfilov.delivery.feature.shippingmethod.ui.ShippingMethodScreen
import ru.nikpanfilov.delivery.feature.signin.SignInDestination
import ru.nikpanfilov.delivery.feature.signin.ui.SignInScreen
import ru.nikpanfilov.delivery.presentation.MainIntent
import ru.nikpanfilov.delivery.ui.NavBar
import javax.inject.Inject

class MainActivity : ComponentActivity() {

	@Inject
	lateinit var navControllerHolder: NavControllerHolder

	private val mainViewModel by lazyViewModel { stateHandle ->
		(applicationContext as App).appComponent.mainViewModel.create(stateHandle)
	}

	private val calculationViewModel by lazyViewModel { stateHandle ->
		(applicationContext as App).appComponent.calculationViewModel.create(stateHandle)
	}

	private val signInViewModel by lazyViewModel { stateHandle ->
		(applicationContext as App).appComponent.signInViewModel.create(stateHandle)
	}

	private val profileViewModel by lazyViewModel { stateHandle ->
		(applicationContext as App).appComponent.profileViewModel.create(stateHandle)
	}

	private val personalInfoViewModel by lazyViewModel { stateHandle ->
		(applicationContext as App).appComponent.personalInfoViewModel.create(stateHandle)
	}

	private val addressInfoViewModel by lazyViewModel { stateHandle ->
		(applicationContext as App).appComponent.addressInfoViewModel.create(stateHandle)
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		(applicationContext as App).appComponent.inject(this)

		enableEdgeToEdge()
		setContent {
			val mainState by mainViewModel.uiState.collectAsState()
			val navController = rememberNavController()
			navControllerHolder.setNavController(navController)

			Screen {
				Scaffold(
					bottomBar = {
						NavBar(
							currentScreen = mainState.currentDestination,
							onOpenCalculation = { mainViewModel.applyIntent(MainIntent.OpenCalculation) },
							onOpenHistory = { mainViewModel.applyIntent(MainIntent.OpenHistory) },
							onOpenOpenProfile = { mainViewModel.applyIntent(MainIntent.OpenProfile) },
						)
					},
				) {
					NavHost(
						modifier = Modifier.padding(it),
						navController = navController,
						startDestination = CalculationDestination,
					) {
						composable<CalculationDestination> {
							mainViewModel.applyIntent(MainIntent.SetOpenedScreen(CalculationDestination))
							val state = calculationViewModel.uiState.collectAsState()

							CalculationScreen(
								state = state.value,
								applyIntent = { calculationViewModel.applyIntent(it) },
							)
						}
						composable<SignInDestination> {
							mainViewModel.applyIntent(MainIntent.SetOpenedScreen(SignInDestination))
							val state = signInViewModel.uiState.collectAsState()

							SignInScreen(
								state = state.value,
								applyIntent = { signInViewModel.applyIntent(it) },
							)
						}
						composable<ProfileDestination> {
							mainViewModel.applyIntent(MainIntent.SetOpenedScreen(ProfileDestination))
							val state = profileViewModel.uiState.collectAsState()

							ProfileScreen(
								state = state.value,
								applyIntent = { profileViewModel.applyIntent(it) },
							)
						}
						composable<ShippingMethodDestination> {
							val dest = it.toRoute<ShippingMethodDestination>()
							val shippingMethodViewModel = remember {
								lazyViewModel { stateHandle ->
									(applicationContext as App).appComponent.shippingMethodViewModel.create(
										stateHandle,
										Json.decodeFromString(dest.deliveryOptions)
									)
								}
							}

							mainViewModel.applyIntent(MainIntent.SetOpenedScreen(dest))
							val state = shippingMethodViewModel.value.uiState.collectAsState()

							ShippingMethodScreen(
								state = state.value,
								applyIntent = { shippingMethodViewModel.value.applyIntent(it) },
							)
						}
						composable<PersonalInfoDestination> {
							mainViewModel.applyIntent(MainIntent.SetOpenedScreen(PersonalInfoDestination))
							val state = personalInfoViewModel.uiState.collectAsState()

							PersonalInfoScreen(
								state = state.value,
								applyIntent = { personalInfoViewModel.applyIntent(it) },
							)
						}
						composable<AddressInfoDestination> {
							mainViewModel.applyIntent(MainIntent.SetOpenedScreen(AddressInfoDestination))
							val state = addressInfoViewModel.uiState.collectAsState()

							AddressInfoScreen(
								state = state.value,
								applyIntent = { addressInfoViewModel.applyIntent(it) },
							)
						}
					}
				}
			}
		}
	}

	override fun onDestroy() {
		navControllerHolder.removeNavController()
		super.onDestroy()
	}
}