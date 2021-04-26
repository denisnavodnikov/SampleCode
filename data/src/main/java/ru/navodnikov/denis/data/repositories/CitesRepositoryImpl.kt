package ru.navodnikov.denis.data.repositories

import android.content.Context
import ru.navodnikov.denis.data.storage.Storage
import ru.navodnikov.denis.domain.entity.IslandEnum.*
import ru.navodnikov.denis.domain.repositories.CitesRepository

class CitesRepositoryImpl(private val context: Context, private val storage: Storage) : CitesRepository {

    override fun getListOfCites(island: Int) : Array<out String> {
        return when (island) {
            LUZON.ordinal -> context.resources.getStringArray(storage.getListOfCitesLuzon())
            CEBU.ordinal -> context.resources.getStringArray(storage.getListOfCitesCebu())
            BORACAY.ordinal -> context.resources.getStringArray(storage.getListOfCitesBoracay())
            BOHOL.ordinal -> context.resources.getStringArray(storage.getListOfCitesBohol())
            PALAVAN.ordinal -> context.resources.getStringArray(storage.getListOfCitesPalavan())
            NEGROS.ordinal -> context.resources.getStringArray(storage.getListOfCitesNegros())
            else -> { arrayOf()}
        }
    }

}