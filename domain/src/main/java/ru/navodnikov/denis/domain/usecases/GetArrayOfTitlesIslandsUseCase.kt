package ru.navodnikov.denis.domain.usecases

import ru.navodnikov.denis.domain.repositories.IslandRepository

class GetArrayOfTitlesIslandsUseCase(private val islandRepository: IslandRepository) {
    fun execute(): Array<String> {
        return islandRepository.getArrayOfTitlesIslands()
    }
}