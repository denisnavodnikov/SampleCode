package ru.navodnikov.denis.karagatantour.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.navodnikov.denis.domain.entity.ExcursionDomain
import ru.navodnikov.denis.domain.entity.IslandEnum
import ru.navodnikov.denis.karagatantour.entity.Excursion
import ru.navodnikov.denis.karagatantour.ui.basket.BasketViewModel
import ru.navodnikov.denis.karagatantour.ui.contacts.ContactsViewModel
import ru.navodnikov.denis.karagatantour.ui.excursion.ExcursionViewModel
import ru.navodnikov.denis.karagatantour.ui.excursions.ExcursionsViewModel
import ru.navodnikov.denis.karagatantour.ui.hotel_order.HotelOrderViewModel
import ru.navodnikov.denis.karagatantour.ui.island.IslandViewModel
import ru.navodnikov.denis.karagatantour.ui.order.OrderViewModel
import ru.navodnikov.denis.karagatantour.ui.start.StartViewModel
import ru.navodnikov.denis.karagatantour.ui.transfer_order.TransferOrderViewModel

val appModule = module {
    viewModel { StartViewModel(getListOfIslandsUseCase = get()) }
    viewModel { (island: IslandEnum) -> IslandViewModel(getIslandScreenUseCase = get(), island = island) }
    viewModel { (island: IslandEnum) -> ExcursionsViewModel(getListOfExcursionsUseCase = get(), island = island) }
    viewModel { ContactsViewModel() }
    viewModel {
        OrderViewModel(
            getTypesOfContacts = get(),
            sendOrdersUseCase = get()
        )
    }
    viewModel { BasketViewModel(getListOfOrdersUseCase = get(), deleteOrderUseCase = get()) }
    viewModel { (excursion: Excursion) ->
        ExcursionViewModel(
            excursion = excursion,
            orderExcursionUseCase = get()
        )
    }
    viewModel { (island: IslandEnum) ->
        HotelOrderViewModel(
            getListOfCitesUseCase = get(),
            island = island,
            orderHotelUseCase = get()
        )
    }
    viewModel {
        TransferOrderViewModel(
            getArrayOfTitlesIslandsUseCase = get(),
            getListOfCitesUseCase = get(),
            orderTransferUseCase = get()
        )
    }
}
