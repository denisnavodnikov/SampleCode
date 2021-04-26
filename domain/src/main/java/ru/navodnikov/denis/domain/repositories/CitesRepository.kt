package ru.navodnikov.denis.domain.repositories

interface CitesRepository {
    fun getListOfCites(island: Int): Array<out String>
}