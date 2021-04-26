package ru.navodnikov.denis.domain.usecases

import ru.navodnikov.denis.domain.repositories.OrderRepository

class OrderTransferUseCase(private val orderRepository: OrderRepository) {
    suspend fun execute(
        cityFrom: String,
        cityTo: String,
        numberAdults: Int,
        numberChildren: Int,
        date: String,
        comments: String
    ) {
        orderRepository.orderTransfer(
            cityFrom = cityFrom,
            cityTo = cityTo,
            numberAdults = numberAdults,
            numberChildren = numberChildren,
            date = date,
            comments = comments
        )
    }

}