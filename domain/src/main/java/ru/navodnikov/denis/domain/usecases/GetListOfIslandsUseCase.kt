package ru.navodnikov.denis.domain.usecases

import ru.navodnikov.denis.domain.entity.Island
import ru.navodnikov.denis.domain.repositories.IslandRepository

class GetListOfIslandsUseCase(private val islandRepository: IslandRepository) {
    fun execute(): List<Island> {
        return islandRepository.getListOfIslands()
    }
}