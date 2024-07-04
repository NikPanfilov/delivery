package ru.nikpanfilov.delivery.core.presentation

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

interface EventHandler<E : Event> {

	val uiEvent: Flow<E>

	fun setEvent(event: E)
}

class EventHandlerImpl<E : Event> : EventHandler<E> {

	private val _uiEvent = Channel<E>(capacity = Channel.CONFLATED)
	override val uiEvent: Flow<E> = _uiEvent.receiveAsFlow()

	override fun setEvent(event: E) {
		_uiEvent.trySend(event)
	}
}