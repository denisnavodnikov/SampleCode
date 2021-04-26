package ru.navodnikov.denis.domain.usecases

import ru.navodnikov.denis.domain.entity.IslandEnum
import ru.navodnikov.denis.domain.entity.IslandScreen
import ru.navodnikov.denis.domain.repositories.IslandRepository

class GetIslandScreenUseCase(private val islandRepository: IslandRepository) {
    fun execute(island: IslandEnum): IslandScreen {
        return islandRepository.getIslandScreen(island)
    }
}