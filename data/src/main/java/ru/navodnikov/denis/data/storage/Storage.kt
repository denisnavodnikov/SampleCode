package ru.navodnikov.denis.data.storage

import ru.navodnikov.denis.data.entity.ExcursionData
import ru.navodnikov.denis.data.entity.IslandData

interface Storage {
    fun getListOfExcursionCebu(): List<ExcursionData>
    fun getListOfExcursionLuzon(): List<ExcursionData>
    fun getListOfExcursionPalavan(): List<ExcursionData>
    fun getListOfExcursionBoracay(): List<ExcursionData>
    fun getListOfExcursionBohol(): List<ExcursionData>
    fun getListOfExcursionNegros(): List<ExcursionData>

    fun getListOfIslands(): List<IslandData>

    fun getTransferUri(): String
    fun getHotelUri(): String
    fun getExcursionPalavanUrl(): String
    fun getExcursionNegrosUrl(): String
    fun getExcursionBoracayUrl(): String
    fun getExcursionBoholUrl(): String
    fun getExcursionCebuUrl(): String
    fun getExcursionLuzonUrl(): String
    fun getIslandTitle(island: Int): Int


    fun getListOfCitesLuzon(): Int
    fun getListOfCitesCebu(): Int
    fun getListOfCitesBoracay(): Int
    fun getListOfCitesBohol(): Int
    fun getListOfCitesPalavan(): Int
    fun getListOfCitesNegros(): Int
    fun getListOfContacts(): Int
}