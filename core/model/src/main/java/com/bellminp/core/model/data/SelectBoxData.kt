package com.bellminp.core.model.data

data class SelectBoxData(
    val id : Long,
    val name : String,
    val isShowImage : Boolean = false,
    val imageUrl : String? = null
)
