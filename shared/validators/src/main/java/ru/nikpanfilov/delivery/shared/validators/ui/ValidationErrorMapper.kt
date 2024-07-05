package ru.nikpanfilov.delivery.shared.validators.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ru.nikpanfilov.delivery.shared.validators.R
import ru.nikpanfilov.delivery.shared.validators.domain.ValidationState
import ru.nikpanfilov.delivery.shared.validators.domain.code.CodeInvalidState
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
