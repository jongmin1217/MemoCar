package com.bellminp.core.model.data


enum class SelectStateType(
    val index : Int,
    val title : String
){
    CATEGORY(0, "카테고리"),
    BRAND(1,"브랜드"),
    ENGINE(2, CarInfoType.ENGINE.text),
    SUPERCHARGING(3, CarInfoType.SUPERCHARGING.text),
    ENGINE_POSITION(4, CarInfoType.ENGINE_POSITION.text),
    DRIVE_METHOD(5, CarInfoType.DRIVE_METHOD.text),
    TRANSMISSION(6, CarInfoType.TRANSMISSION.text),
    DESIGN(7, CarInfoType.DESIGN.text)
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
