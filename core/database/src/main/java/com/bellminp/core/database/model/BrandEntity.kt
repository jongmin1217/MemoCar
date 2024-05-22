package com.bellminp.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bellminp.core.model.data.Brand
import com.bellminp.core.model.data.Category


@Entity(tableName = "brand")
data class BrandEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Long,
    val name : String,
    val imageUrl : String?
)

fun BrandEntity.asExternalModel() = Brand(
    id = id,
    name = name,
    imageUrl = imageUrl
)