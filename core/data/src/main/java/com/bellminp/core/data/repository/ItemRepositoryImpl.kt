package com.bellminp.core.data.repository

import com.bellminp.core.data.model.asEntity
import com.bellminp.core.database.dao.ItemDao
import com.bellminp.core.database.model.ItemEntity
import com.bellminp.core.database.model.SettingEntity
import com.bellminp.core.database.model.asExternalModel
import com.bellminp.core.model.data.Item
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ItemRepositoryImpl @Inject constructor(
    private val itemDao: ItemDao
) : ItemRepository{
    override fun getItemList() = itemDao.getItemList().map { it.map(ItemEntity::asExternalModel) }

    override suspend fun upsertItem(itemList: List<Item>) = itemDao.upsertItem(itemList.map { it.asEntity() })

    override suspend fun deleteItem(id: Long) = itemDao.deleteItem(id)
}