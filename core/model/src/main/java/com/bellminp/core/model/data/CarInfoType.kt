package com.bellminp.core.model.data

enum class CarInfoType(
    val id : Int,
    val text : String
) {
    ENGINE(0, "엔진형식"),
    SUPERCHARGING(1,"과급방식"),
    ENGINE_POSITION(2,"엔진위치"),
    DRIVE_METHOD(3,"구동방식"),
    TRANSMISSION(4,"변속기"),
    DESIGN(5,"외형")
}