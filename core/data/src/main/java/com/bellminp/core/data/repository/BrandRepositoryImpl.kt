package com.bellminp.core.data.repository

import com.bellminp.core.data.model.asEntity
import com.bellminp.core.database.dao.BrandDao
import com.bellminp.core.database.model.BrandEntity
import com.bellminp.core.database.model.CategoryEntity
import com.bellminp.core.database.model.asExternalModel
import com.bellminp.core.model.data.Brand
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BrandRepositoryImpl @Inject constructor(
    private val brandDao: BrandDao
) : BrandRepository{
    override fun getBrandList() = brandDao.getBrandList().map { it.map(BrandEntity::asExternalModel) }

    override suspend fun insertBrand(brand: Brand) = brandDao.insertBrand(brand.asEntity())

    override suspend fun updateBrand(brand: Brand) = brandDao.updateBrand(brand.asEntity())

    override suspend fun deleteBrand(id: Long) = brandDao.deleteBrand(id)
}