package com.bellminp.core.domain

import com.bellminp.core.data.repository.CarRepository
import javax.inject.Inject

class CarUseCase @Inject constructor(
    private val carRepository: CarRepository
) {
    fun getAllSettingList() = carRepository.getAllSettingList()

}