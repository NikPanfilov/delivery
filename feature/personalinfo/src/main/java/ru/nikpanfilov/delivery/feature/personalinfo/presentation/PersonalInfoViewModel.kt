package ru.nikpanfilov.delivery.feature.personalinfo.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch
import ru.nikpanfilov.delivery.core.error.presentation.ErrorDelegate
import ru.nikpanfilov.delivery.core.error.presentation.asCoroutineExceptionHandler
import ru.nikpanfilov.delivery.core.presentation.BaseViewModel
import ru.nikpanfilov.delivery.core.token.domain.usecase.IsTokenExistUseCase
import ru.nikpanfilov.delivery.shared.deliveryinfo.domain.entity.PersonalInfo
import ru.nikpanfilov.delivery.shared.deliveryinfo.domain.usecase.LoadReceiverInfoUseCase
import ru.nikpanfilov.delivery.shared.deliveryinfo.domain.usecase.LoadSenderInfoUseCase
import ru.nikpanfilov.delivery.shared.deliveryinfo.domain.usecase.SaveReceiverInfoUseCase
import ru.nikpanfilov.delivery.shared.deliveryinfo.domain.usecase.SaveSenderInfoUseCase
import ru.nikpanfilov.delivery.shared.user.domain.usecase.GetUserUseCase
import ru.nikpanfilov.delivery.shared.validators.domain.isValid
import ru.nikpanfilov.delivery.shared.validators.domain.name.NamePart
import ru.nikpanfilov.delivery.shared.validators.domain.name.ValidateNameUseCase
import ru.nikpanfilov.delivery.shared.validators.domain.name.isValid
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
		when (intent) {
			is PersonalInfoIntent.LoadData         -> handleLoadData()
			is PersonalInfoIntent.ChangeFirstname  -> handleChangeFirstname(intent.name)
			is PersonalInfoIntent.ChangeMiddlename -> handleChangeMiddlename(intent.name)
			is PersonalInfoIntent.ChangeLastname   -> handleChangeLastname(intent.name)
			is PersonalInfoIntent.ChangePhone      -> handleChangePhone(intent.phone)
			is PersonalInfoIntent.Next             -> handleNext()
			is PersonalInfoIntent.NavigateBack     -> handleNavigateBack()
		}
	}

	private fun handleLoadData() {
		val state = uiState.value as? PersonalInfoState.Content
		val personalInfo = if (uiState.value !is PersonalInfoState.Content ||
			state?.page == InfoPage.SENDER_INFO
		) {
			loadSenderInfoUseCase().also {
				if (it == null && isTokenExistsUseCase()) {
					loadUser()
				}
			}
		} else {
			loadReceiverInfoUseCase()
		}

		personalInfo?.let {
			setState(
				PersonalInfoState.Content(
					page = state?.page ?: InfoPage.SENDER_INFO,
					firstname = validateNameUseCase(it.firstname, NamePart.NAME),
					middlename = validateNameUseCase(it.middlename, NamePart.PATRONYMIC),
					lastname = validateNameUseCase(it.lastname, NamePart.SURNAME),
					phone = validatePhoneUseCase(it.phone),
					dataValid = true,
				)
			)
		}
	}

	private fun loadUser() {
		setState(PersonalInfoState.Loading)
		viewModelScope.launch(errorHandler) {
			getUserUseCase().let {
				setState(
					PersonalInfoState.Content(
						page = InfoPage.SENDER_INFO,
						firstname = validateNameUseCase(it.firstname, NamePart.NAME),
						middlename = validateNameUseCase(it.middlename, NamePart.PATRONYMIC),
						lastname = validateNameUseCase(it.lastname, NamePart.SURNAME),
						phone = validatePhoneUseCase(it.phone),
						dataValid = true,
					)
				)
			}
		}
	}

	private fun handleChangeFirstname(name: String) {
		val state = uiState.value as? PersonalInfoState.Content ?: return
		setState(
			state.copy(
				firstname = validateNameUseCase(data = name, namePart = NamePart.NAME),
			)
		)
		checkDataValid()
	}

	private fun checkDataValid() {
		val state = uiState.value as? PersonalInfoState.Content ?: return
		with(state) {
			setState(
				copy(
					dataValid = firstname.isValid() && middlename.isValid() && lastname.isValid() && phone.isValid()
				)
			)
		}
	}

	private fun handleChangeMiddlename(name: String) {
		val state = uiState.value as? PersonalInfoState.Content ?: return
		setState(
			state.copy(
				middlename = validateNameUseCase(data = name, namePart = NamePart.PATRONYMIC),
			)
		)
		checkDataValid()

	}

	private fun handleChangeLastname(name: String) {
		val state = uiState.value as? PersonalInfoState.Content ?: return
		setState(
			state.copy(
				lastname = validateNameUseCase(data = name, namePart = NamePart.SURNAME),
			)
		)
		checkDataValid()
	}

	private fun handleChangePhone(phone: String) {
		val state = uiState.value as? PersonalInfoState.Content ?: return
		setState(
			state.copy(
				phone = validatePhoneUseCase(phone),
			)
		)
		checkDataValid()
	}

	private fun handleNext() {
		val state = uiState.value as? PersonalInfoState.Content ?: return
		with(state) {
			val personalInfo = PersonalInfo(
				firstname = firstname.data,
				middlename = middlename.data,
				lastname = lastname.data,
				phone = phone.data,
			)

			if (page == InfoPage.SENDER_INFO) {
				saveSenderInfoUseCase(personalInfo)
				setState(copy(page = InfoPage.RECEIVER_INFO))
				handleLoadData()
			} else {
				saveReceiverInfoUseCase(personalInfo)
				router.navigateToAddressInfo()
			}
		}
	}

	private fun handleNavigateBack() {
		val state = uiState.value as? PersonalInfoState.Content ?: return
		if (state.page == InfoPage.SENDER_INFO) {
			router.navigateBack()
		} else {
			setState(state.copy(page = InfoPage.SENDER_INFO))
			handleLoadData()
		}
	}

	@AssistedFactory
	interface Factory {

		fun create(savedStateHandle: SavedStateHandle): PersonalInfoViewModel
	}
}