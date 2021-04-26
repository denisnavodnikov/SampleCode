package ru.navodnikov.denis.domain.usecases

import ru.navodnikov.denis.domain.repositories.CitesRepository

class GetListOfCitesUseCase(private val citesRepository: CitesRepository) {
    fun execute(island: Int): Array<out String> {
        return citesRepository.getListOfCites(island)
    }
}