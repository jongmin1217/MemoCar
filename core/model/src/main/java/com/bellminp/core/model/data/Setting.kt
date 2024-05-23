package com.bellminp.core.model.data

data class Setting(
    val id : Long,
    val type : Int,
    val name : String
) : SwipeItem {
    override fun getItemId() = id
    override fun getItemName() = name
    override fun getImageState() = ImageState(
        isShow = false,
        imageUrl = null
    )
}


