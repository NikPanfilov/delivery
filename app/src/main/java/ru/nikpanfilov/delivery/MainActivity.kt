package ru.nikpanfilov.delivery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import ru.nikpanfilov.delivery.core.navigation.NavControllerHolder
import ru.nikpanfilov.delivery.core.ui.compose.Screen
import javax.inject.Inject

class MainActivity : ComponentActivity() {

	@Inject
	lateinit var navControllerHolder: NavControllerHolder

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		(applicationContext as App).appComponent.inject(this)

		enableEdgeToEdge()
		setContent {
			val navController = rememberNavController()
			navControllerHolder.setNavController(navController)

			Screen {

			}
		}
	}

	override fun onPause() {
		super.onPause()
		navControllerHolder.removeNavController()
	}
}