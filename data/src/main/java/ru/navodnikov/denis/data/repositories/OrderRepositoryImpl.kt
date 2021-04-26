package ru.navodnikov.denis.data.repositories

import android.content.Context
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import ru.navodnikov.denis.data.R
import ru.navodnikov.denis.data.db.dao.ToursDAO
import ru.navodnikov.denis.data.entity.*
import ru.navodnikov.denis.data.networking.TelegramApiService
import ru.navodnikov.denis.data.storage.Storage
import ru.navodnikov.denis.domain.entity.Excursion
import ru.navodnikov.denis.domain.entity.Order
import ru.navodnikov.denis.domain.repositories.OrderRepository

class OrderRepositoryImpl(
    private val storageDB: ToursDAO,
    private val context: Context,
    private val storage: Storage,
    private val telegramApiService: TelegramApiService
) : OrderRepository {
    override suspend fun orderExcursion(
        excursion: Excursion,
        numberAdults: Int,
        numberChildren: Int,
        date: String,
        price: String
    ) {
        storageDB.insert(
            OrderData(
                typeOfOrder = context.getText(R.string.excursion).toString(),
                title = context.resources.getString(excursion.title),
                imageUri = excursion.imageUri,
                numberOfAdult = context.getString(R.string.adults_number, numberAdults),
                numberOfChildren = when (numberChildren) {
                    ZERO -> EMPTY_TEXT
                    ONE -> context.getString(R.string.children_number_1, numberChildren)
                    else -> context.getString(R.string.children_number_2, numberChildren)
                },
                date = date,
                price = price,
                comments = EMPTY_TEXT
            )
        )
    }

    override suspend fun orderTransfer(
        cityFrom: String,
        cityTo: String,
        numberAdults: Int,
        numberChildren: Int,
        date: String,
        comments: String
    ) {
        storageDB.insert(
            OrderData(
                typeOfOrder = context.getText(R.string.transfer).toString(),
                title = context.resources.getString(R.string.transfer_title_order, cityFrom, cityTo),
                imageUri = storage.getTransferUri(),
                numberOfAdult = context.getString(R.string.adults_number, numberAdults),
                numberOfChildren = when (numberChildren) {
                    ZERO -> EMPTY_TEXT
                    1 -> context.getString(R.string.children_number_1, numberChildren)
                    else -> context.getString(R.string.children_number_2, numberChildren)
                },
                date = date,
                price = EMPTY_TEXT,
                comments = comments
            )
        )
    }

    override suspend fun orderHotel(
        city: String,
        rating: Int,
        numberAdults: Int,
        numberChildren: Int,
        dateSince: String,
        dateBefore: String,
        comments: String
    ) {
        storageDB.insert(
            OrderData(
                typeOfOrder = context.getText(R.string.hotel_booking).toString(),
                title = when (rating) {
                    ONE -> context.resources.getString(R.string.hotel_title_order_1, rating)
                    TWO, THREE, FORE -> context.resources.getString(R.string.hotel_title_order_2_3_4, rating)
                    FIVE -> context.resources.getString(R.string.hotel_title_order_5, rating)
                    else -> EMPTY_TEXT
                },
                imageUri = storage.getHotelUri(),
                numberOfAdult = context.getString(R.string.adults_number, numberAdults),
                numberOfChildren = when (numberChildren) {
                    ZERO -> EMPTY_TEXT
                    ONE -> context.getString(R.string.children_number_1, numberChildren)
                    else -> context.getString(R.string.children_number_2, numberChildren)
                },
                date = context.resources.getString(R.string.date_hotel_order, dateSince, dateBefore),
                price = EMPTY_TEXT,
                comments = comments
            )
        )
    }

    override fun getOrders(): Flow<List<Order>> {
        return storageDB.getAllOrders().map { list -> list.map { it.mapToDomain() } }


    }

    override suspend fun deleteOrder(order: Order) {
        storageDB.clearOrders(
            OrderData(
                id = order.id,
                typeOfOrder = order.typeOfOrder,
                imageUri = order.imageUri,
                title = order.title,
                numberOfAdult = order.numberOfAdult,
                numberOfChildren = order.numberOfChildren,
                date = order.date,
                comments = order.comments,
                price = order.price
            )
        )
    }

    override fun getListOfTypesOfContacts(): Array<String> {
        return context.resources.getStringArray(storage.getListOfContacts())
    }

    override suspend fun sendOrder(name: String, phone: String, email: String, typeOfContact: String): Boolean {
        val message: StringBuilder = StringBuilder()
        message.append(
            context.resources.getString(
                R.string.order_contacts_pattern,
                name,
                phone,
                email,
                typeOfContact
            )
        )
        val orders = storageDB.getAllOrders().first()
        for (order in orders) {
            message.append(order.typeOfOrder, BREAK_LINE)
            message.append(order.title, BREAK_LINE)
            message.append(order.numberOfAdult, " ")
            if(order.numberOfChildren.isNotEmpty()){
                message.append(order.numberOfChildren, BREAK_LINE)
            }else{
                message.append(BREAK_LINE)
            }
            message.append(order.date, BREAK_LINE)
            if(order.price.isNotEmpty()){
                message.append(order.price, BREAK_LINE)
            }
            if(order.comments.isNotEmpty()){
                message.append(order.comments, BREAK_LINE)
            }
            message.append(BREAK_LINE)
        }

        val resultIsOk = telegramApiService.sendMassage(message.toString()).ok
        if(resultIsOk){
            for(order in getOrders().first()){
                deleteOrder(order)
            }
        }
        return resultIsOk
    }
}