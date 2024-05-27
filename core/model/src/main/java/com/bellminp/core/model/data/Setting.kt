package com.bellminp.core.model.data

data class Setting(
    val id : Long,
    val type : Int,
    val name : String,
    val displayOrder : Int,
    val isShowImage : Boolean,
    val imageUrl : String?
)


data class SettingPagerData(
    val type : SelectStateType,
    val list : List<Setting>
)

fun Setting.toSelectData() = SelectBoxData(
    id = id,
    name = name,
    isShowImage = isShowImage,
    imageUrl = imageUrl
)


