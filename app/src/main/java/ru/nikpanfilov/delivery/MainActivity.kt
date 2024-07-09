package ru.nikpanfilov.delivery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.nikpanfilov.delivery.core.navigation.NavControllerHolder
import ru.nikpanfilov.delivery.core.presentation.lazyViewModel
import ru.nikpanfilov.delivery.core.ui.compose.Screen
import ru.nikpanfilov.delivery.feature.calculation.presentation.CalculationDestination
import ru.nikpanfilov.delivery.feature.calculation.ui.CalculationScreen
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