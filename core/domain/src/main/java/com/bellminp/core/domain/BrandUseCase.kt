package com.bellminp.core.domain

import com.bellminp.core.data.repository.BrandRepository
import com.bellminp.core.model.data.Brand
import com.bellminp.core.model.data.Category
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class BrandUseCase @Inject constructor(
    private val brandRepository: BrandRepository
) {
    fun getBrandList(): Flow<List<Brand>> = brandRepository.getBrandList()

    suspend fun deleteBrand(id : Long) = brandRepository.deleteBrand(id)

    suspend fun insertBrand() = brandRepository.insertBrand(Brand(id = 0, name = "", imageUrl = null))

    suspend fun updateBrand(brand : Brand) = brandRepository.updateBrand(brand)
}