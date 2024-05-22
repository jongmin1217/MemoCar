package com.bellminp.core.data.repository

import com.bellminp.core.database.model.BrandEntity
import com.bellminp.core.model.data.Brand
import kotlinx.coroutines.flow.Flow

interface BrandRepository {

    fun getBrandList() : Flow<List<Brand>>
    suspend fun insertBrand(brand : Brand) : Long
    suspend fun updateBrand(brand: Brand)
    suspend fun deleteBrand(id: Long)

}