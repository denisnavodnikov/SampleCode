package ru.navodnikov.denis.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.navodnikov.denis.data.db.dao.ToursDAO
import ru.navodnikov.denis.data.entity.OrderData

@Database(entities = [(OrderData::class)], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getReposDao(): ToursDAO

    companion object {
        const val NAME = "orders_db.db"
    }

}