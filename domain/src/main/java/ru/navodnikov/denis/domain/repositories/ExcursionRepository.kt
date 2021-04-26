package ru.navodnikov.denis.domain.repositories

import ru.navodnikov.denis.domain.entity.Excursion
import ru.navodnikov.denis.domain.entity.IslandEnum

interface ExcursionRepository {
    fun getListOfExcursions(island: IslandEnum): List<Excursion>
}