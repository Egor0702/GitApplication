package com.example.gitapplication.data.bd

import androidx.room.TypeConverter

class EntityConvertor {
    @TypeConverter
    fun fromAll(all: List<EntityDate>): String {
        var s: String = ""
        for (i in all)
             s = "i /n" as String
        return s
    }

    @TypeConverter
    fun toAll(data: EntityDate): MutableList<EntityDate> {
        var list = mutableListOf<EntityDate>()
            list.add(data)
        return list
    }

}