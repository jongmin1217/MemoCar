package com.bellminp.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bellminp.core.model.data.Align
import com.bellminp.core.model.data.Format
import com.bellminp.core.model.data.InputType
import com.bellminp.core.model.data.Item
import com.bellminp.core.model.data.Type


@Entity(tableName = "item")
data class ItemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val title: String,
    val inputType: InputTypeEntity,
    val displayOrder : Int
)

data class InputTypeEntity(
    val type: TypeEntity,
    val format: FormatEntity? = null,
    val isDecimal: Boolean? = null,
    val align: AlignEntity? = null,
    val isShowDashBoard : Boolean? = null,
    val hasImage : Boolean = false,
    val subText: String? = null
)

enum class TypeEntity(val value: Int) {
    WRITE(0), SELECT(1), IMAGE(2)
}

enum class FormatEntity(val value: Int) {
    TEXT(0), NUMBER(1)
}

enum class AlignEntity(val value: Int) {
    LEFT(0), CENTER(1), RIGHT(2)
}

fun ItemEntity.asExternalModel() = Item(
    id = id,
    title = title,
    inputType = inputType.asExternalModel(),
    displayOrder = displayOrder
)


fun InputTypeEntity.asExternalModel() = InputType(
    type = type.asExternalModel(),
    format = format?.asExternalModel(),
    isDecimal = isDecimal,
    align = align?.asExternalModel(),
    isShowDashBoard = isShowDashBoard,
    hasImage = hasImage,
    subText = subText
)

fun TypeEntity.asExternalModel() = when (this) {
    TypeEntity.WRITE -> Type.WRITE
    TypeEntity.SELECT -> Type.SELECT
    TypeEntity.IMAGE -> Type.IMAGE
}


fun FormatEntity.asExternalModel() = when (this) {
    FormatEntity.TEXT -> Format.TEXT
    FormatEntity.NUMBER -> Format.NUMBER
}


fun AlignEntity.asExternalModel() = when (this) {
    AlignEntity.LEFT -> Align.LEFT
    AlignEntity.CENTER -> Align.CENTER
    AlignEntity.RIGHT -> Align.RIGHT
}