package ru.nikpanfilov.delivery.core.ui.ext

import androidx.lifecycle.LifecycleCoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

fun <T> Flow<T>.subscribe(scope: LifecycleCoroutineScope, block: (T) -> Unit) {
	this.onEach(block).launchIn(scope)
}