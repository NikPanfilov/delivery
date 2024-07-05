package ru.nikpanfilov.delivery.shared.validators.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ru.nikpanfilov.delivery.shared.validators.R
import ru.nikpanfilov.delivery.shared.validators.domain.ValidationState
import ru.nikpanfilov.delivery.shared.validators.domain.address.AddressInvalidState
import ru.nikpanfilov.delivery.shared.validators.domain.city.CityInvalidState
import ru.nikpanfilov.delivery.shared.validators.domain.code.CodeInvalidState
import ru.nikpanfilov.delivery.shared.validators.domain.courier.CourierInvalidState
import ru.nikpanfilov.delivery.shared.validators.domain.email.EmailInvalidState
import ru.nikpanfilov.delivery.shared.validators.domain.empty.EmptyInvalidState
import ru.nikpanfilov.delivery.shared.validators.domain.name.NameInvalidState
import ru.nikpanfilov.delivery.shared.validators.domain.phone.PhoneInvalidState

@Composable
fun getPhoneError(state: ValidationState): String? =
	when ((state as? ValidationState.Invalid)?.invalidState as? PhoneInvalidState) {
		PhoneInvalidState.EMPTY              -> stringResource(R.string.empty)
		PhoneInvalidState.RESTRICTED_SYMBOLS -> stringResource(R.string.phone_restricted_symbols)
		else                                 -> null
	}

@Composable
fun getCodeError(state: ValidationState): String? =
	when ((state as? ValidationState.Invalid)?.invalidState as? CodeInvalidState) {
		CodeInvalidState.EMPTY              -> stringResource(R.string.empty)
		CodeInvalidState.RESTRICTED_SYMBOLS -> stringResource(R.string.code_restricted_symbols)
		CodeInvalidState.WRONG_LENGTH       -> stringResource(R.string.code_wrong_length)
		else                                -> null
	}

@Composable
fun getNotEmptyError(state: ValidationState): String? =
	when ((state as? ValidationState.Invalid)?.invalidState as? EmptyInvalidState) {
		EmptyInvalidState -> stringResource(R.string.empty)
		else              -> null
	}

@Composable
fun getNameError(state: ValidationState): String? =
	when ((state as? ValidationState.Invalid)?.invalidState as? NameInvalidState) {
		NameInvalidState.EMPTY               -> stringResource(R.string.empty)
		NameInvalidState.INCORRECT_FORMAT    -> stringResource(R.string.name_restricted_symbols)
		NameInvalidState.TOO_LONG            -> stringResource(R.string.name_too_long)
		NameInvalidState.DIFFERENT_ALPHABETS -> stringResource(R.string.name_different_alphabets)
		else                                 -> null
	}

@Composable
fun getAddressError(state: ValidationState): String? =
	when ((state as? ValidationState.Invalid)?.invalidState as? AddressInvalidState) {
		AddressInvalidState.EMPTY               -> stringResource(R.string.empty)
		AddressInvalidState.INCORRECT_FORMAT    -> stringResource(R.string.address_incorrect_format)
		AddressInvalidState.TOO_LONG            -> stringResource(R.string.address_too_long)
		AddressInvalidState.DIFFERENT_ALPHABETS -> stringResource(R.string.address_different_alphabets)
		else                                    -> null
	}

@Composable
fun getCourierError(state: ValidationState): String? =
	when ((state as? ValidationState.Invalid)?.invalidState as? CourierInvalidState) {
		CourierInvalidState.TOO_LONG -> stringResource(R.string.courier_too_long)
		else                         -> null
	}

@Composable
fun getCityError(state: ValidationState): String? =
	when ((state as? ValidationState.Invalid)?.invalidState as? CityInvalidState) {
		CityInvalidState.EMPTY               -> stringResource(R.string.empty)
		CityInvalidState.INCORRECT_FORMAT    -> stringResource(R.string.city_restricted_symbols)
		CityInvalidState.TOO_LONG            -> stringResource(R.string.city_too_long)
		CityInvalidState.DIFFERENT_ALPHABETS -> stringResource(R.string.city_different_alphabets)
		else                                 -> null
	}

@Composable
fun getEmailError(state: ValidationState): String? =
	when ((state as? ValidationState.Invalid)?.invalidState as? EmailInvalidState) {
		EmailInvalidState.EMPTY            -> stringResource(R.string.empty)
		EmailInvalidState.INCORRECT_FORMAT -> stringResource(R.string.email_incorrect_format)
		else                               -> null
	}