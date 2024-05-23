package com.bellminp.core.database.util

import androidx.room.TypeConverter

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