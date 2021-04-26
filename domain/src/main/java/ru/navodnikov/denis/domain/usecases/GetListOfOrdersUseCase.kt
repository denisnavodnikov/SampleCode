package ru.navodnikov.denis.domain.usecases

import kotlinx.coroutines.flow.Flow
import ru.navodnikov.denis.domain.entity.Order
import ru.navodnikov.denis.domain.repositories.OrderRepository

class GetListOfOrdersUseCase(private val orderRepository: OrderRepository) {
    fun execute(): Flow<List<Order>> {
        return orderRepository.getOrders()
    }
}