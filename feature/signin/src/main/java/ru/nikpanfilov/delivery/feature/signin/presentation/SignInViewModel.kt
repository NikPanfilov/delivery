package ru.nikpanfilov.delivery.feature.signin.presentation

import androidx.lifecycle.SavedStateHandle
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import ru.nikpanfilov.delivery.core.error.presentation.ErrorDelegate
import ru.nikpanfilov.delivery.core.error.presentation.asCoroutineExceptionHandler
import ru.nikpanfilov.delivery.core.presentation.BaseViewModel
import ru.nikpanfilov.delivery.feature.signin.domain.usecase.SendOtpCodeUseCase
import ru.nikpanfilov.delivery.feature.signin.domain.usecase.SignInUseCase
import ru.nikpanfilov.delivery.shared.validators.domain.DefaultValidationItem
import ru.nikpanfilov.delivery.shared.validators.domain.code.ValidateCodeUseCase
import ru.nikpanfilov.delivery.shared.validators.domain.phone.ValidatePhoneUseCase

class SignInViewModel @AssistedInject constructor(
	private val validatePhoneUseCase: ValidatePhoneUseCase,
	private val validateCodeUseCase: ValidateCodeUseCase,
	private val sendOtpCodeUseCase: SendOtpCodeUseCase,
	private val signInUseCase: SignInUseCase,
	private val router: SignInRouter,
	errorDelegate: ErrorDelegate,
	@Assisted savedStateHandle: SavedStateHandle,
) : BaseViewModel<SignInState, SignInIntent>() {

	override fun initState(): SignInState = SignInState(
		phone = DefaultValidationItem(),
		codeStatus = CodeStatus.NotSent,
		loadingStatus = LoadingStatus.None,
		dataValid = false,
	)

	val errorHandler = errorDelegate.asCoroutineExceptionHandler {
		setState(uiState.value.copy(loadingStatus = LoadingStatus.Error))
	}

	override fun applyIntent(intent: SignInIntent) {
	}

	@AssistedFactory
	interface Factory {

		fun create(savedStateHandle: SavedStateHandle): SignInViewModel
	}
}