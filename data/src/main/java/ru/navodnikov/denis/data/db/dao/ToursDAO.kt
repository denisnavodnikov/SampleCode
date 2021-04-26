package ru.navodnikov.denis.data.db.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ru.navodnikov.denis.data.entity.OrderData

@Dao
interface ToursDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(orderData: OrderData)

    @Delete
    suspend fun clearOrders(vararg orderData: OrderData)

    @Query("SELECT * FROM orders ORDER BY typeOfOrder ASC")
    fun getAllOrders(): Flow<List<OrderData>>
}