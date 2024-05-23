package com.bellminp.core.data.repository

import com.bellminp.core.database.dao.BrandDao
import com.bellminp.core.database.dao.CategoryDao
import com.bellminp.core.database.dao.SettingDao
import com.bellminp.core.database.model.BrandEntity
import com.bellminp.core.database.model.CategoryEntity
import com.bellminp.core.database.model.SettingEntity
import com.bellminp.core.database.model.asExternalModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CarRepositoryImpl @Inject constructor(
    private val settingDao: SettingDao,
    private val brandDao: BrandDao,
    private val categoryDao: CategoryDao
) : CaeRepository{
    override fun getAllSettingList() = settingDao.getAllSettingList().map { it.map(SettingEntity::asExternalModel) }
    override fun getBrandList() = brandDao.getBrandList().map { it.map(BrandEntity::asExternalModel) }
    override fun getCategoryList() = categoryDao.getCategoryList().map { it.map(CategoryEntity::asExternalModel) }
}