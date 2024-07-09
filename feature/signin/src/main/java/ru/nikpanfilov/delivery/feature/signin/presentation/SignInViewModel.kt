package ru.nikpanfilov.delivery.feature.signin.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.nikpanfilov.delivery.core.error.presentation.ErrorDelegate
import ru.nikpanfilov.delivery.core.error.presentation.asCoroutineExceptionHandler
import ru.nikpanfilov.delivery.core.presentation.BaseViewModel
import ru.nikpanfilov.delivery.feature.signin.domain.entity.SignIn
import ru.nikpanfilov.delivery.feature.signin.domain.usecase.SendOtpCodeUseCase
import ru.nikpanfilov.delivery.feature.signin.domain.usecase.SignInUseCase
import ru.nikpanfilov.delivery.shared.validators.domain.DefaultValidationItem
import ru.nikpanfilov.delivery.shared.validators.domain.code.ValidateCodeUseCase
import ru.nikpanfilov.delivery.shared.validators.domain.isValid
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

	companion object {

		private const val SECOND = 1000L
	}

	override fun applyIntent(intent: SignInIntent) {
		when (intent) {
			is SignInIntent.ChangePhone  -> handleChangePhone(intent.phone)
			is SignInIntent.ResetPhone   -> handleResetPhone()
			is SignInIntent.ChangeCode   -> handleChangeCode(intent.code)
			is SignInIntent.Next         -> handleNext()
			is SignInIntent.ResendCode   -> handleResendCode()
			is SignInIntent.NavigateBack -> handleNavigateBack()
		}
	}

	private fun handleChangePhone(phone: String) {
		if (uiState.value.codeStatus is CodeStatus.Sent) {
			return
		}

		setState(uiState.value.copy(phone = validatePhoneUseCase(phone)))
		checkDataValid()
	}

	private fun checkDataValid() {
		val state = uiState.value
		with(state) {
			setState(
				copy(
					dataValid = if (codeStatus is CodeStatus.Sent) {
						codeStatus.code.isValid()
					} else {
						phone.isValid()
					}
				)
			)
		}
	}

	private fun handleResetPhone() {
		setState(initState())
	}

	private fun handleChangeCode(code: String) {
		val state = uiState.value
		if (state.codeStatus !is CodeStatus.Sent) {
			return
		}

		setState(state.copy(codeStatus = state.codeStatus.copy(code = validateCodeUseCase(code))))

		checkDataValid()
	}

	private fun handleNext() {
		if (uiState.value.codeStatus is CodeStatus.Sent) {
			signIn()
		} else {
			handleResendCode()
		}
	}

	private fun signIn() {
		val state = uiState.value
		if (state.codeStatus !is CodeStatus.Sent) {
			return
		}

		viewModelScope.launch(errorHandler) {
			setState(uiState.value.copy(loadingStatus = LoadingStatus.Loading))
			signInUseCase(
				SignIn(
					phone = state.phone.data,
					code = state.codeStatus.code.data.toInt(),
				)
			)
			router.setNewRoot()
		}
	}

	private fun handleResendCode() {
		viewModelScope.launch(errorHandler) {
			setState(uiState.value.copy(loadingStatus = LoadingStatus.Loading))
			val resendDelay = sendOtpCodeUseCase(uiState.value.phone.data)
			setState(
				uiState.value.copy(
					loadingStatus = LoadingStatus.None,
					codeStatus = CodeStatus.Sent(
						code = DefaultValidationItem(),
						retryDelay = resendDelay,
					)
				)
			)
			startTimer()
		}
	}

	private fun startTimer() {
		viewModelScope.launch(Dispatchers.IO) {
			while ((uiState.value.codeStatus as? CodeStatus.Sent)?.retryDelay != null) {
				delay(SECOND)
				with(uiState.value) {
					if (codeStatus is CodeStatus.Sent) {
						if ((codeStatus.retryDelay ?: 0.0) > SECOND) {
							setState(copy(codeStatus = codeStatus.copy(retryDelay = codeStatus.retryDelay?.minus(SECOND))))
						} else {
							setState(copy(codeStatus = codeStatus.copy(retryDelay = null)))
						}
					}
				}
			}
		}
	}

	private fun handleNavigateBack() {
		router.navigateBack()
	}

	@AssistedFactory
	interface Factory {

		fun create(savedStateHandle: SavedStateHandle): SignInViewModel
	}
}