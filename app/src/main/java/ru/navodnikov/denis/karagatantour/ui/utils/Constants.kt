package ru.navodnikov.denis.karagatantour.ui.utils

enum class Intents {
    GEO,MAIL,WHATSAPP,TELEGRAM,INSTAGRAM,FACEBOOK,WEBSITE,VK
}
class Constants{
    companion object {
        const val TRANSFERS: String = "Transfers"
        const val EMPTY_TEXT: String = ""
        const val ZERO = 0
        const val SIZE_BUTTON = 20
        const val DISCOUNT_10: Double = 0.9
        const val DISCOUNT_15: Double = 0.85
        const val DISCOUNT_20: Double = 0.8
        const val DISCOUNT_30: Double = 0.7

        const val BASE_URL: String = "https://api.telegram.org/"
    }
}

