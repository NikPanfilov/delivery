package ru.nikpanfilov.delivery.feature.calculation.presentation

import ru.nikpanfilov.delivery.core.presentation.State
import ru.nikpanfilov.delivery.feature.calculation.domain.entity.PackageType
import ru.nikpanfilov.delivery.shared.cities.domain.entity.DeliveryPoint

sealed interface CalculationState : State {

	data object Initial : CalculationState
	data object Loading : CalculationState
	data object Error : CalculationState

	data class Content(
		val sendPoint: DeliveryPoint,
		val receiverPoint: DeliveryPoint,
		val deliveryPointOptions: List<DeliveryPoint>,
		val packageType: PackageType,
		val packageTypeOptions: List<PackageType>,
		val detailsChange: DetailsChange?,
	) : CalculationState
}

enum class DetailsChange {
	SEND_POINT,
	RECEIVER_POINT,
	PACKAGE_TYPE,
}