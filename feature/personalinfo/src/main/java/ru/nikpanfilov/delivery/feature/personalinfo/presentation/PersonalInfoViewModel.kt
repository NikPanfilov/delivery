package ru.nikpanfilov.delivery.feature.personalinfo.presentation

import androidx.lifecycle.SavedStateHandle
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import ru.nikpanfilov.delivery.core.error.presentation.ErrorDelegate
import ru.nikpanfilov.delivery.core.error.presentation.asCoroutineExceptionHandler
import ru.nikpanfilov.delivery.core.presentation.BaseViewModel
import ru.nikpanfilov.delivery.core.token.domain.usecase.IsTokenExistUseCase
import ru.nikpanfilov.delivery.shared.deliveryinfo.domain.usecase.LoadReceiverInfoUseCase
import ru.nikpanfilov.delivery.shared.deliveryinfo.domain.usecase.LoadSenderInfoUseCase
import ru.nikpanfilov.delivery.shared.deliveryinfo.domain.usecase.SaveReceiverInfoUseCase
import ru.nikpanfilov.delivery.shared.deliveryinfo.domain.usecase.SaveSenderInfoUseCase
import ru.nikpanfilov.delivery.shared.validators.domain.DefaultValidationItem
import ru.nikpanfilov.delivery.shared.user.domain.usecase.GetUserUseCase
import ru.nikpanfilov.delivery.shared.validators.domain.name.NamePart
import ru.nikpanfilov.delivery.shared.validators.domain.name.NameValidationItem
import ru.nikpanfilov.delivery.shared.validators.domain.name.ValidateNameUseCase
import ru.nikpanfilov.delivery.shared.validators.domain.phone.ValidatePhoneUseCase

class PersonalInfoViewModel @AssistedInject constructor(
	private val validateNameUseCase: ValidateNameUseCase,
	private val validatePhoneUseCase: ValidatePhoneUseCase,
	private val isTokenExistsUseCase: IsTokenExistUseCase,
	private val getUserUseCase: GetUserUseCase,
	private val saveSenderInfoUseCase: SaveSenderInfoUseCase,
	private val saveReceiverInfoUseCase: SaveReceiverInfoUseCase,
	private val loadSenderInfoUseCase: LoadSenderInfoUseCase,
	private val loadReceiverInfoUseCase: LoadReceiverInfoUseCase,
	private val router: PersonalInfoRouter,
	errorDelegate: ErrorDelegate,
	@Assisted savedStateHandle: SavedStateHandle,
) : BaseViewModel<PersonalInfoState, PersonalInfoIntent>() {

	override fun initState(): PersonalInfoState = PersonalInfoState.Initial

	private val errorHandler = errorDelegate.asCoroutineExceptionHandler {
		setState(PersonalInfoState.Error)
	}

	override fun applyIntent(intent: PersonalInfoIntent) {
	}

	@AssistedFactory
	interface Factory {

		fun create(savedStateHandle: SavedStateHandle): PersonalInfoViewModel
	}
}