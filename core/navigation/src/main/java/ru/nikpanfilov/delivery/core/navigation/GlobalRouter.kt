package ru.nikpanfilov.delivery.core.navigation

interface GlobalRouter {

	fun navigateTo(destination: Destination)

	fun newRootScreen(destination: Destination)

	fun navigateBack()
}