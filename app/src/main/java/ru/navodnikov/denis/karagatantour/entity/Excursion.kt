package ru.navodnikov.denis.karagatantour.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Excursion(
    val title: Int,
    val imageUri: String,
    val body: Int,
    val priceAdult: Int,
    val priceChild: Int
) : Parcelable