package ru.navodnikov.denis.domain.usecases

import ru.navodnikov.denis.domain.entity.ExcursionDomain
import ru.navodnikov.denis.domain.entity.IslandEnum
import ru.navodnikov.denis.domain.repositories.ExcursionRepository

class GetListOfExcursionsUseCase(private val excursionRepository: ExcursionRepository) {
    fun execute(island: IslandEnum): List<ExcursionDomain> {
        return excursionRepository.getListOfExcursions(island)
    }
}