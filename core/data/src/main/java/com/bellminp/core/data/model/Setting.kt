package com.bellminp.core.data.model

import com.bellminp.core.database.model.SettingEntity
import com.bellminp.core.model.data.Setting

fun Setting.asEntity() = SettingEntity(
    id = id,
    type = type,
    name = name,
    displayOrder = displayOrder,
    isShowImage = isShowImage,
    imageUrl = imageUrl
)