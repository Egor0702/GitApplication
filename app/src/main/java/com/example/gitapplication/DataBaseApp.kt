package com.example.gitapplication

import androidx.room.Database
import androidx.room.RoomDatabase
@Database(entities = arrayOf(EntityDate::class), version = 1)
abstract class DataBaseApp : RoomDatabase(){
    abstract fun repositoryDao(): RepositoryDao
}