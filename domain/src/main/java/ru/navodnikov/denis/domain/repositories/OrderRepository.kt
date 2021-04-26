package ru.navodnikov.denis.domain.repositories

import kotlinx.coroutines.flow.Flow
import ru.navodnikov.denis.domain.entity.Excursion
import ru.navodnikov.denis.domain.entity.Order

interface OrderRepository {
    suspend fun orderExcursion(
        excursion: Excursion,
        numberAdults: Int,
        numberChildren: Int,
        date: String,
        price: String
    )

    suspend fun orderTransfer(
        cityFrom: String,
        cityTo: String,
        numberAdults: Int,
        numberChildren: Int,
        date: String,
        comments: String
    )

    suspend fun orderHotel(
        city: String,
        rating: Int,
        numberAdults: Int,
        numberChildren: Int,
        dateSince: String,
        dateBefore: String,
        comments: String
    )

    fun getOrders(): Flow<List<Order>>
    suspend fun deleteOrder(order: Order)
    fun getListOfTypesOfContacts(): Array<String>
    suspend fun sendOrder(name: String, phone: String, email: String, typeOfContact: String): Boolean
}