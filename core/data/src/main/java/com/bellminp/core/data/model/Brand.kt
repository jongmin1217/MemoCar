package com.bellminp.core.data.model

import com.bellminp.core.database.model.BrandEntity
import com.bellminp.core.database.model.CategoryEntity
import com.bellminp.core.model.data.Brand

fun Brand.asEntity() = BrandEntity(
    id = id,
    name = name,
    imageUrl = imageUrl
)