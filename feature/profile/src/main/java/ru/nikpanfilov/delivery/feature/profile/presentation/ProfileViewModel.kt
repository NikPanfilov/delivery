package ru.nikpanfilov.delivery.feature.profile.presentation

import androidx.lifecycle.SavedStateHandle
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import ru.nikpanfilov.delivery.core.error.presentation.ErrorDelegate
import ru.nikpanfilov.delivery.core.error.presentation.asCoroutineExceptionHandler
import ru.nikpanfilov.delivery.core.presentation.BaseViewModel
import ru.nikpanfilov.delivery.feature.profile.domain.usecase.UpdateUserUseCase
import ru.nikpanfilov.delivery.shared.cities.domain.entity.DeliveryPoint
import ru.nikpanfilov.delivery.shared.cities.domain.usecase.GetDeliveryCitiesUseCase
import ru.nikpanfilov.delivery.shared.user.domain.usecase.GetUserUseCase
import ru.nikpanfilov.delivery.shared.validators.domain.email.ValidateEmailUseCase
import ru.nikpanfilov.delivery.shared.validators.domain.name.ValidateNameUseCase

class ProfileViewModel @AssistedInject constructor(
	private val getUserUseCase: GetUserUseCase,
	private val updateUserUseCase: UpdateUserUseCase,
	private val getDeliveryCitiesUseCase: GetDeliveryCitiesUseCase,
	private val validateNameUseCase: ValidateNameUseCase,
	private val validateEmailUseCase: ValidateEmailUseCase,
	private val router: ProfileRouter,
	errorDelegate: ErrorDelegate,
	@Assisted savedStateHandle: SavedStateHandle,
) : BaseViewModel<ProfileState, ProfileIntent>() {

	override fun initState(): ProfileState = ProfileState.Initial

	val errorHandler = errorDelegate.asCoroutineExceptionHandler {
		setState(ProfileState.Error)
	}

	override fun applyIntent(intent: ProfileIntent) {
		TODO("Not yet implemented")
	}

	@AssistedFactory
	interface Factory {

		fun create(savedStateHandle: SavedStateHandle): ProfileViewModel
	}
}