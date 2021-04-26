package ru.navodnikov.denis.domain.usecases

import ru.navodnikov.denis.domain.entity.Excursion
import ru.navodnikov.denis.domain.repositories.OrderRepository

class OrderExcursionUseCase(private val orderRepository: OrderRepository) {
    suspend fun execute(excursion: Excursion, numberAdults: Int, numberChildren: Int, date: String, price: String) {
        orderRepository.orderExcursion(excursion, numberAdults, numberChildren, date, price)
    }

}