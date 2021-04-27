package ru.navodnikov.denis.domain.repositories

import ru.navodnikov.denis.domain.entity.ExcursionDomain
import ru.navodnikov.denis.domain.entity.IslandEnum

interface ExcursionRepository {
    fun getListOfExcursions(island: IslandEnum): List<ExcursionDomain>
}