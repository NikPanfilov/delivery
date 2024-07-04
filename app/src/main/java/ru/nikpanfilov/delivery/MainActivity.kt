package ru.nikpanfilov.delivery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.nikpanfilov.delivery.core.navigation.Destination
import ru.nikpanfilov.delivery.core.navigation.NavControllerHolder
import ru.nikpanfilov.delivery.core.ui.compose.Screen
import ru.nikpanfilov.delivery.feature.signin.presentation.SignInDestination
import ru.nikpanfilov.delivery.feature.signin.ui.SignInScreen
import javax.inject.Inject

class MainActivity : ComponentActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		(applicationContext as App).appComponent.inject(this)

		enableEdgeToEdge()
		setContent {

		}
	}
}