package ru.navodnikov.denis.domain.repositories

import ru.navodnikov.denis.domain.entity.Island
import ru.navodnikov.denis.domain.entity.IslandEnum
import ru.navodnikov.denis.domain.entity.IslandScreen

interface IslandRepository {
    fun getListOfIslands(): List<Island>
    fun getIslandScreen(island: IslandEnum): IslandScreen
    fun getArrayOfTitlesIslands(): Array<String>
}