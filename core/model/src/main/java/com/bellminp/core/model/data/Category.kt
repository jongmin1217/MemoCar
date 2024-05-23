package com.bellminp.core.model.data

data class Category(
    val id : Long,
    val name : String,
    val displayOrder : Int
) : SwipeItem {
    override fun getItemId() = id
    override fun getItemName() = name
    override fun getImageState() = ImageState(
        isShow = false,
        imageUrl = null
    )
}
