package com.bellminp.core.data.model

import com.bellminp.core.database.model.AlignEntity
import com.bellminp.core.database.model.FormatEntity
import com.bellminp.core.database.model.InputTypeEntity
import com.bellminp.core.database.model.ItemEntity
import com.bellminp.core.database.model.TypeEntity
import com.bellminp.core.model.data.Align
import com.bellminp.core.model.data.Format
import com.bellminp.core.model.data.InputType
import com.bellminp.core.model.data.Item
import com.bellminp.core.model.data.Type


fun Item.asEntity() = ItemEntity(
    id = id,
    title = title,
    inputType = inputType.asEntity(),
    displayOrder = displayOrder
)


fun InputType.asEntity() = InputTypeEntity(
    type = type.asEntity(),
    format = format?.asEntity(),
    isDecimal = isDecimal,
    align = align?.asEntity(),
    isShowDashBoard = isShowDashBoard,
    hasImage = hasImage,
    subText = subText
)

fun Type.asEntity() = when (this) {
    Type.WRITE -> TypeEntity.WRITE
    Type.SELECT -> TypeEntity.SELECT
    Type.IMAGE -> TypeEntity.IMAGE
}


fun Format.asEntity() = when (this) {
    Format.TEXT -> FormatEntity.TEXT
    Format.NUMBER -> FormatEntity.NUMBER
}


fun Align.asEntity() = when (this) {
    Align.LEFT -> AlignEntity.LEFT
    Align.CENTER -> AlignEntity.CENTER
    Align.RIGHT -> AlignEntity.RIGHT
}