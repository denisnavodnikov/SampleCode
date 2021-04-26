package ru.navodnikov.denis.domain.usecases

import ru.navodnikov.denis.domain.repositories.OrderRepository

class SendOrdersUseCase(private val orderRepository: OrderRepository) {
    suspend fun execute(name: String, phone: String, email: String, typeOfContact: String): Boolean {
        return orderRepository.sendOrder(name, phone, email, typeOfContact)
    }

}