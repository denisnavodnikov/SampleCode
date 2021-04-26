package ru.navodnikov.denis.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = TABLE_NAME)
data class OrderData(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val typeOfOrder: String,
    val imageUri: String,
    val title: String,
    val numberOfAdult: String,
    val numberOfChildren: String,
    val date: String,
    val price: String,
    val comments: String
)