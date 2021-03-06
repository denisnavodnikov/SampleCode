package ru.navodnikov.denis.karagatantour.ui.utils

import ru.navodnikov.denis.domain.entity.*

const val DISCOUNT_10: Double = 0.9
const val DISCOUNT_15: Double = 0.85
const val DISCOUNT_20: Double = 0.8
const val DISCOUNT_30: Double = 0.7

fun countPrice(adults: Int, children: Int, excursionDomain: ExcursionDomain): Int {
    return when {
        adults in 1..2 -> {
            (adults * excursionDomain.priceAdult) + (children * excursionDomain.priceChild)
        }
        adults in 3..4 -> {
            (((adults * excursionDomain.priceAdult) + (children * excursionDomain.priceChild)) * DISCOUNT_10).toInt() / 5 * 5
        }
        adults in 5..6 -> {
            (((adults * excursionDomain.priceAdult) + (children * excursionDomain.priceChild)) * DISCOUNT_15).toInt() / 5 * 5
        }
        adults in 7..8 -> {
            (((adults * excursionDomain.priceAdult) + (children * excursionDomain.priceChild)) * DISCOUNT_20).toInt() / 5 * 5
        }
        adults > 8 -> {
            (((adults * excursionDomain.priceAdult) + (children * excursionDomain.priceChild)) * DISCOUNT_30).toInt() / 5 * 5
        }
        else -> 0
    }
}

