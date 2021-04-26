package ru.navodnikov.denis.domain.usecases

import ru.navodnikov.denis.domain.repositories.OrderRepository

class OrderHotelUseCase(private val orderRepository: OrderRepository) {
    suspend fun execute(
        city: String,
        rating: Int,
        numberAdults: Int,
        numberChildren: Int,
        dateSince: String,
        dateBefore: String,
        comments: String
    ) {
        orderRepository.orderHotel(
            city = city,
            rating = rating,
            numberAdults = numberAdults,
            numberChildren = numberChildren,
            dateSince = dateSince,
            dateBefore = dateBefore,
            comments = comments
        )
    }

}