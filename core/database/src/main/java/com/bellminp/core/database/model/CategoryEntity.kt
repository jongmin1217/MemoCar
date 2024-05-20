package com.bellminp.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bellminp.core.model.data.Category

@Entity(tableName = "category")
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Long,
    val name : String,
    val displayOrder : Int
)

fun CategoryEntity.asExternalModel() = Category(
    id = id,
    name = name,
    displayOrder = displayOrder
)