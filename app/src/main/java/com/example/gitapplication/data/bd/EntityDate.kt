package com.example.gitapplication.data.bd

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.File

@Entity
data class EntityDate (
    @PrimaryKey val id: Int,
    @ColumnInfo val nameAccount: String,
    @ColumnInfo val nameRepository: String
)
