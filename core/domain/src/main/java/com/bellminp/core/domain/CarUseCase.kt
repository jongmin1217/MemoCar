package com.bellminp.core.domain

import com.bellminp.core.data.repository.CaeRepository
import javax.inject.Inject

class CarUseCase @Inject constructor(
    private val carRepository: CaeRepository
) {
    fun getAllSettingList() = carRepository.getAllSettingList()
    fun getCategoryList() = carRepository.getCategoryList()
    fun getBrandList() = carRepository.getBrandList()

}