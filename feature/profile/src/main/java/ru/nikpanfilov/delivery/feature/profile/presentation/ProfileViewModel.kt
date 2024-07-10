package ru.nikpanfilov.delivery.feature.profile.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch
import ru.nikpanfilov.delivery.core.error.presentation.ErrorDelegate
import ru.nikpanfilov.delivery.core.error.presentation.asCoroutineExceptionHandler
import ru.nikpanfilov.delivery.core.presentation.BaseViewModel
import ru.nikpanfilov.delivery.feature.profile.domain.usecase.UpdateUserUseCase
import ru.nikpanfilov.delivery.shared.cities.domain.entity.DeliveryPoint
import ru.nikpanfilov.delivery.shared.cities.domain.usecase.GetDeliveryCitiesUseCase
import ru.nikpanfilov.delivery.shared.user.domain.entity.User
import ru.nikpanfilov.delivery.shared.user.domain.usecase.GetUserUseCase
import ru.nikpanfilov.delivery.shared.validators.domain.DefaultValidationItem
import ru.nikpanfilov.delivery.shared.validators.domain.email.ValidateEmailUseCase
import ru.nikpanfilov.delivery.shared.validators.domain.isValid
import ru.nikpanfilov.delivery.shared.validators.domain.name.NamePart
import ru.nikpanfilov.delivery.shared.validators.domain.name.NameValidationItem
import ru.nikpanfilov.delivery.shared.validators.domain.name.ValidateNameUseCase
import ru.nikpanfilov.delivery.shared.validators.domain.name.isValid

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
		when (intent) {
			is ProfileIntent.LoadData         -> handleLoadData()
			is ProfileIntent.ChangeFirstname  -> handleChangeFirstname(intent.name)
			is ProfileIntent.ChangeMiddlename -> handleChangeMiddlename(intent.name)
			is ProfileIntent.ChangeLastname   -> handleChangeLastname(intent.name)
			is ProfileIntent.ChangeEmail      -> handleChangeEmail(intent.email)
			is ProfileIntent.ChangeCity -> handleChangeCity()
			is ProfileIntent.SetCity          -> handleSetCity(intent.city)
			is ProfileIntent.SaveData         -> handleSaveData()
			is ProfileIntent.NavigateBack     -> handleNavigateBack()
		}
	}

	private fun handleLoadData() {
		setState(ProfileState.Loading)
		viewModelScope.launch(errorHandler) {
			val user = getUserUseCase()
			val cities = getDeliveryCitiesUseCase()
			setState(
				ProfileState.Content(
					firstname = NameValidationItem(
						data = user.firstname,
						namePart = NamePart.NAME,
					),
					middlename = NameValidationItem(
						data = user.middlename,
						namePart = NamePart.PATRONYMIC,
					),
					lastname = NameValidationItem(
						data = user.lastname,
						namePart = NamePart.SURNAME,
					),
					city = cities.find { it.name == user.city },
					cityOptions = cities,
					phone = user.phone,
					email = DefaultValidationItem(user.email, optional = true),
					dataValid = true,
					changingCity = false,
				)
			)
		}
	}

	private fun handleChangeFirstname(name: String) {
		val state = uiState.value as? ProfileState.Content ?: return
		setState(
			state.copy(
				firstname = validateNameUseCase(
					data = name,
					namePart = NamePart.NAME,
				)
			)
		)
		checkDataValid()
	}

	private fun checkDataValid() {
		val state = uiState.value as? ProfileState.Content ?: return
		setState(
			state.copy(
				dataValid = state.firstname.isValid() &&
					state.middlename.isValid() &&
					state.lastname.isValid() &&
					state.email.isValid(),
			)
		)
	}

	private fun handleChangeMiddlename(name: String) {
		val state = uiState.value as? ProfileState.Content ?: return
		setState(
			state.copy(
				middlename = validateNameUseCase(
					data = name,
					namePart = NamePart.PATRONYMIC,
				)
			)
		)
		checkDataValid()
	}

	private fun handleChangeLastname(name: String) {
		val state = uiState.value as? ProfileState.Content ?: return
		setState(
			state.copy(
				lastname = validateNameUseCase(
					data = name,
					namePart = NamePart.SURNAME,
				)
			)
		)
		checkDataValid()
	}

	private fun handleChangeEmail(email: String) {
		val state = uiState.value as? ProfileState.Content ?: return
		setState(
			state.copy(
				email = validateEmailUseCase(email),
			)
		)
		checkDataValid()
	}

	private fun handleChangeCity() {
		val state = uiState.value as? ProfileState.Content ?: return
		setState(
			state.copy(
				changingCity = !state.changingCity,
			)
		)
	}

	private fun handleSetCity(city: DeliveryPoint) {
		val state = uiState.value as? ProfileState.Content ?: return
		setState(
			state.copy(
				city = city,
				changingCity = false,
			)
		)
	}

	private fun handleSaveData() {
		val state = uiState.value as? ProfileState.Content ?: return
		setState(ProfileState.Loading)
		viewModelScope.launch(errorHandler) {
			updateUserUseCase(
				User(
					phone = state.phone,
					firstname = state.firstname.data,
					middlename = state.middlename.data,
					lastname = state.lastname.data,
					email = state.email.data,
					city = state.city?.name ?: "",
				)
			)
			setState(state)
		}
	}

	private fun handleNavigateBack() {
		router.navigateBack()
	}

	@AssistedFactory
	interface Factory {

		fun create(savedStateHandle: SavedStateHandle): ProfileViewModel
	}
}