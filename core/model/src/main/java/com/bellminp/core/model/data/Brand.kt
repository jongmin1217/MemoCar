package com.bellminp.core.model.data

data class Brand(
    val id : Long,
    val name : String,
    val imageUrl : String?
) : SwipeItem {
    override fun getItemId() = id
    override fun getItemName() = name
    override fun getImageState() = ImageState(
        isShow = true,
        imageUrl = imageUrl
    )
}