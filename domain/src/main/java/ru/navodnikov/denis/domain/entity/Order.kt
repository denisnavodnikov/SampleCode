package ru.navodnikov.denis.domain.entity

data class Order(
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