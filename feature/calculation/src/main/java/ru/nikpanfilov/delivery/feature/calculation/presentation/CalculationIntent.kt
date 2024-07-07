package ru.nikpanfilov.delivery.feature.calculation.presentation

import ru.nikpanfilov.delivery.core.presentation.Intent
import ru.nikpanfilov.delivery.feature.calculation.domain.entity.PackageType
import ru.nikpanfilov.delivery.shared.cities.domain.entity.DeliveryPoint

sealed interface CalculationIntent : Intent {

	data object LoadData : CalculationIntent

	data class SetSenderCity(val city: DeliveryPoint) : CalculationIntent
	data class SetReceiverCity(val city: DeliveryPoint) : CalculationIntent
	data class SetPackageType(val packageType: PackageType) : CalculationIntent

	data object ChangeSenderCity : CalculationIntent
	data object ChangeReceiverCity : CalculationIntent
	data object ChangePackageType : CalculationIntent
	data object CancelChangeDetails : CalculationIntent

	data object Calculate : CalculationIntent
}