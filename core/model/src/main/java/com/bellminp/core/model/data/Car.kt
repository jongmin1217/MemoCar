package com.bellminp.core.model.data


enum class SelectStateType(
    val id : Int,
    val title : String
) : PagerItem {
    CATEGORY(0, "카테고리") {
        override fun getTabId() = id
        override fun getTabTile() = title
    },
    BRAND(1,"브랜드") {
        override fun getTabId() = id
        override fun getTabTile() = title
    },
    ENGINE(2, "엔진형식") {
        override fun getTabId() = id
        override fun getTabTile() = title
    },
    SUPERCHARGING(3, "과급방식") {
        override fun getTabId() = id
        override fun getTabTile() = title
    },
    ENGINE_POSITION(4, "엔진위치") {
        override fun getTabId() = id
        override fun getTabTile() = title
    },
    DRIVE_METHOD(5, "구동방식") {
        override fun getTabId() = id
        override fun getTabTile() = title
    },
    TRANSMISSION(6, "변속기") {
        override fun getTabId() = id
        override fun getTabTile() = title
    },
    DESIGN(7, "외형") {
        override fun getTabId() = id
        override fun getTabTile() = title
    }
}


enum class InputStateType(
    val index : Int,
    val isDecimal : Boolean,
    val isNumber: Boolean,
    val label : String,
    val descriptionText : String?
){
    NAME(0, false, false, "모델명", null),
    HP(1, false, true, "출력","hp"),
    ENGINE_CC(2, true, true, "배기량","cc"),
    TOP_SPEED(3, false, true, "최고 속도","km"),
    ACCELERATION_PERFORMANCE(4, false, true, "가속 성능","sec"),
    PRICE(5, true, true, "가격","원"),
    RESELL_PRICE(6, true, true, "리셀 가격","원")
}


enum class ColorStateType(
    val index : Int,
    val title : String
){
    EXTERIOR_COLOR(0,"실외 색상"),
    INTERIOR_COLOR(1,"실내 색상")
}


enum class ImageStateType(
    val index: Int,
    val title : String
){
    IMAGE(0, "이미지")
}
