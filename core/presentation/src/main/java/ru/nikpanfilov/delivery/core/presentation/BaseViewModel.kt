package ru.nikpanfilov.delivery.core.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

abstract class BaseViewModel<S : State, I : Intent> : ViewModel() {

	private val _uiState by lazy { MutableStateFlow(initState()) }
	val uiState by lazy { _uiState }

	protected abstract fun initState(): S

	abstract fun applyIntent(intent: I)

	protected fun setState(state: S) {
		_uiState.value = state
	}
}