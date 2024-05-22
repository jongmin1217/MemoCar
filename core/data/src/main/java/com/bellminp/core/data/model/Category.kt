package com.bellminp.core.data.model

import com.bellminp.core.database.model.CategoryEntity
import com.bellminp.core.model.data.Category

fun Category.asEntity() = CategoryEntity(
    id = id,
    name = name,
    displayOrder = displayOrder
)