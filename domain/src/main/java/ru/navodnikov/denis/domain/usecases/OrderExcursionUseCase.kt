package ru.navodnikov.denis.domain.usecases

import ru.navodnikov.denis.domain.entity.ExcursionDomain
import ru.navodnikov.denis.domain.repositories.OrderRepository

class OrderExcursionUseCase(private val orderRepository: OrderRepository) {
    suspend fun execute(
        excursionDomain: ExcursionDomain,
        numberAdults: Int,
        numberChildren: Int,
        date: String,
        price: String
    ) {
        orderRepository.orderExcursion(
            excursionDomain = excursionDomain,
            numberAdults = numberAdults,
            numberChildren = numberChildren,
            date = date,
            price = price
        )
    }

}