package ru.nikpanfilov.delivery.core.navigation

import androidx.navigation.NavController

interface NavControllerHolder {

	fun setNavController(navController: NavController)

	fun removeNavController()
}