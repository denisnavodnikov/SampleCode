package ru.navodnikov.denis.karagatantour.di

import org.koin.dsl.module
import ru.navodnikov.denis.domain.usecases.*

val domainModule = module {
    single { GetArrayOfTitlesIslandsUseCase(islandRepository = get()) }
    single { GetIslandScreenUseCase(islandRepository = get()) }
    single { GetListOfCitesUseCase(citesRepository = get()) }
    single { GetListOfExcursionsUseCase(excursionRepository = get()) }
    single { GetListOfIslandsUseCase(islandRepository = get()) }
    single { OrderExcursionUseCase(orderRepository = get()) }
    single { OrderHotelUseCase(orderRepository = get()) }
    single { OrderTransferUseCase(orderRepository = get()) }
    single { GetListOfOrdersUseCase(orderRepository = get()) }
    single { DeleteOrderUseCase(orderRepository = get()) }
    single { GetTypesOfContacts(orderRepository = get()) }
    single { SendOrdersUseCase(orderRepository = get()) }
}