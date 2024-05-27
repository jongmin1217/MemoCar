package com.bellminp.core.data.repository

import com.bellminp.core.data.model.asEntity
import com.bellminp.core.database.dao.ItemDao
import com.bellminp.core.database.model.ItemEntity
import com.bellminp.core.database.model.asExternalModel
import com.bellminp.core.model.data.Item
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AddItemRepositoryImpl @Inject constructor(
    private val itemDao: ItemDao
) : AddItemRepository{

    override fun getItemList() = itemDao.getItemList().map { it.map(ItemEntity::asExternalModel) }
    override suspend fun insertItem(item: Item) = itemDao.insertItem(item.asEntity())

    override suspend fun updateItem(item: Item) = itemDao.updateItem(item.asEntity())
}