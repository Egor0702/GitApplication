package com.example.gitapplication

import androidx.room.Insert
import androidx.room.Query

interface RepositoryDao {
    @Insert
    fun insert(entityDate: EntityDate)
    @Query("SELECT * FROM entitydate")
    fun getAll(): List<EntityDate>
}