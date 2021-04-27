package ru.navodnikov.denis.data.repositories

import ru.navodnikov.denis.data.entity.mapToDomain
import ru.navodnikov.denis.data.storage.Storage
import ru.navodnikov.denis.domain.entity.ExcursionDomain
import ru.navodnikov.denis.domain.entity.IslandEnum
import ru.navodnikov.denis.domain.repositories.ExcursionRepository

class ExcursionRepositoryImpl(private val storage: Storage) : ExcursionRepository {

    override fun getListOfExcursions(island: IslandEnum): List<ExcursionDomain> {
            return when (island) {
            IslandEnum.LUZON -> storage.getListOfExcursionLuzon()
            IslandEnum.CEBU -> storage.getListOfExcursionCebu()
            IslandEnum.BORACAY -> storage.getListOfExcursionBoracay()
            IslandEnum.BOHOL -> storage.getListOfExcursionBohol()
            IslandEnum.PALAVAN -> storage.getListOfExcursionPalavan()
            IslandEnum.NEGROS -> storage.getListOfExcursionNegros()
        }.map { it.mapToDomain() }
    }



}