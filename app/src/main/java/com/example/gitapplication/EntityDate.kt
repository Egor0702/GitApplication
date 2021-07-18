package com.example.gitapplication

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EntityDate (
        @PrimaryKey
    val id: Int,
    val nameAccount: String,
    val repositoryName : String
)
