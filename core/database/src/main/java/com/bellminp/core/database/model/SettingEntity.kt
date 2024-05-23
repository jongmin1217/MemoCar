package com.bellminp.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bellminp.core.model.data.Setting

@Entity(tableName = "setting")
data class SettingEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Long,
    val type : Int,
    val name : String
)

fun SettingEntity.asExternalModel() = Setting(
    id = id,
    type = type,
    name = name
)