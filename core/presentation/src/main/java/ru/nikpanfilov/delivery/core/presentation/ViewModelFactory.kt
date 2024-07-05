package ru.nikpanfilov.delivery.core.presentation

import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner

class Factory<T : ViewModel>(
	savedStateRegistryOwner: SavedStateRegistryOwner,
	private val create: (stateHandle: SavedStateHandle) -> T,
) : AbstractSavedStateViewModelFactory(savedStateRegistryOwner, null) {

	override fun <T : ViewModel> create(key: String, modelClass: Class<T>, handle: SavedStateHandle): T {
		return create.invoke(handle) as T
	}
}

inline fun <reified T : ViewModel> ComponentActivity.lazyViewModel(
	noinline create: (stateHandle: SavedStateHandle) -> T
) = viewModels<T> {
	Factory(this, create)
}