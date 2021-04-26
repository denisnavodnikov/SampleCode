package ru.navodnikov.denis.data.repositories

import android.content.Context
import ru.navodnikov.denis.data.entity.mapToDomain
import ru.navodnikov.denis.data.storage.Storage
import ru.navodnikov.denis.domain.entity.Island
import ru.navodnikov.denis.domain.entity.IslandEnum
import ru.navodnikov.denis.domain.entity.IslandScreen
import ru.navodnikov.denis.domain.repositories.IslandRepository

class IslandRepositoryImpl(private val storage: Storage, private val context: Context) : IslandRepository {

    override fun getListOfIslands(): List<Island> {
        return storage.getListOfIslands()
            .map { it.mapToDomain() }

    }

    override fun getIslandScreen(island: IslandEnum): IslandScreen {
        return IslandScreen(
            title = storage.getIslandTitle(island.ordinal),
            imageTransfer = storage.getTransferUri(),
            imageExcursion = when (island) {
                IslandEnum.LUZON -> storage.getExcursionLuzonUrl()
                IslandEnum.CEBU -> storage.getExcursionCebuUrl()
                IslandEnum.BOHOL -> storage.getExcursionBoholUrl()
                IslandEnum.BORACAY -> storage.getExcursionBoracayUrl()
                IslandEnum.NEGROS -> storage.getExcursionNegrosUrl()
                IslandEnum.PALAVAN -> storage.getExcursionPalavanUrl()
            },
            imageHotel = storage.getHotelUri()
        )
    }

    override fun getArrayOfTitlesIslands(): Array<String> {
        return storage.getListOfIslands().map { context.resources.getText(it.title).toString() }.toTypedArray()
    }
}