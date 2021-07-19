package com.example.gitapplication.data.bd

import androidx.room.*

@Dao
interface RepositoryDao {
    @Insert
    fun insert(entityDate: EntityDate)
//    @TypeConverters(EntityConvertor::class)
    @Query("SELECT * FROM entitydate")
    fun getAll(): MutableList<EntityDate>
}