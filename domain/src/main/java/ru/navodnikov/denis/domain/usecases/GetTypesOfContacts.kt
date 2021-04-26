package ru.navodnikov.denis.domain.usecases

import ru.navodnikov.denis.domain.repositories.OrderRepository

class GetTypesOfContacts(private val orderRepository: OrderRepository) {
    fun execute(): Array<String> {
        return orderRepository.getListOfTypesOfContacts()
    }
}