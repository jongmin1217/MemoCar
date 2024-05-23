package com.bellminp.core.model.data

interface SwipeItem {
    fun getItemId() : Long
    fun getItemName() : String
    fun getImageState() : ImageState

}

data class ImageState(
    val isShow : Boolean,
    val imageUrl : String?
)