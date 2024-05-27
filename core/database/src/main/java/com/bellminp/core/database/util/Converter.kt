package com.bellminp.core.database.util

import androidx.room.TypeConverter
import com.bellminp.core.database.model.InputTypeEntity
import com.google.gson.Gson

class ListConverter {
    @TypeConverter
    fun fromListString(list: List<String>): String {
        return list.joinToString(",")
    }

    @TypeConverter
    fun toListString(data: String): List<String> {
        return listOf(*data.split(",").map { it }.toTypedArray())
    }
}

class InputTypeConverter{
    @TypeConverter
    fun fromInputType(data : InputTypeEntity): String {
        return Gson().toJson(data)
    }

    @TypeConverter
    fun toInputType(data: String): InputTypeEntity {
        return Gson().fromJson(data,InputTypeEntity::class.java)
    }
}