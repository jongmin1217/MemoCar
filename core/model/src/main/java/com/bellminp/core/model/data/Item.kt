package com.bellminp.core.model.data


data class Item(
    val id : Long,
    val title : String,
    val inputType : InputType,
    val displayOrder : Int
)

data class InputType(
    val type : Type,
    val format : Format? = null,
    val isDecimal : Boolean? = null,
    val align : Align? = null,
    val isShowDashBoard : Boolean? = null,
    val hasImage : Boolean = false,
    val subText : String? = null
)

enum class Type(
    val value : Int,
    val text : String
){
    WRITE(0,"직접입력"),SELECT(1,"체크박스"),IMAGE(2,"이미지")
}

enum class Format(
    val value : Int,
    val text : String
) {
    TEXT(0,"텍스트"),NUMBER(1,"숫자")
}

enum class Align(
    val value : Int,
    val text : String
){
    LEFT(0,"왼쪽정렬"),CENTER(1,"중앙정렬"),RIGHT(2,"우측정렬")
}

fun Type.toSelectData() = SelectBoxData(
    id = value.toLong(),
    name = text
)

fun Format.toSelectData() = SelectBoxData(
    id = value.toLong(),
    name = text
)

fun Align.toSelectData() = SelectBoxData(
    id = value.toLong(),
    name = text
)
