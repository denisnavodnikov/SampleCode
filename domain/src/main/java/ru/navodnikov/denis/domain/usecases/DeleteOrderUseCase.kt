package ru.navodnikov.denis.domain.usecases

import ru.navodnikov.denis.domain.entity.Order
import ru.navodnikov.denis.domain.repositories.OrderRepository

class DeleteOrderUseCase(private val orderRepository: OrderRepository) {
    suspend fun execute(order: Order) {
        orderRepository.deleteOrder(order)
    }
}