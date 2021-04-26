package ru.navodnikov.denis.karagatantour.ui.utils

import ru.navodnikov.denis.domain.entity.Excursion

class Utils {
    companion object {

        fun countPrice(adults: Int, children: Int, excursion: Excursion): Int {
            return when {
                adults in 1..2 -> {
                    (adults * excursion.priceAdult) + (children * excursion.priceChild)
                }
                adults in 3..4 -> {
                    (((adults * excursion.priceAdult) + (children * excursion.priceChild)) * Constants.DISCOUNT_10).toInt() / 5 * 5
                }
                adults in 5..6 -> {
                    (((adults * excursion.priceAdult) + (children * excursion.priceChild)) * Constants.DISCOUNT_15).toInt() / 5 * 5
                }
                adults in 7..8 -> {
                    (((adults * excursion.priceAdult) + (children * excursion.priceChild)) * Constants.DISCOUNT_20).toInt() / 5 * 5
                }
                adults > 8 -> {
                    (((adults * excursion.priceAdult) + (children * excursion.priceChild)) * Constants.DISCOUNT_30).toInt() / 5 * 5
                }
                else -> 0
            }
        }
    }
}