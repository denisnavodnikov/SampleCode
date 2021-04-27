package ru.navodnikov.denis.domain.entity

data class ExcursionDomain(
    val title: Int,
    val imageUri: String,
    val body: Int,
    val priceAdult: Int,
    val priceChild: Int
)