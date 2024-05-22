package com.bellminp.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "car")
data class CarEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Long,
    val brandId : Long,
    val categoryId : Long,
    val name : String,
    val exteriorColor : List<String>,
    val interiorColor : List<String>,
    val hp : Int,
    val engineTypeId : Int,
    val engineCc : Int,
    val superchargingTypeId : Int,
    val enginePositionTypeId : Int,
    val driveMethodTypeId : Int,
    val topSpeed : Int,
    val accelerationPerformance : Float,
    val transmissionTypeId : Int,
    val designTypeId : Int,
    val price : Int,
    val resellPrice :Int?
)