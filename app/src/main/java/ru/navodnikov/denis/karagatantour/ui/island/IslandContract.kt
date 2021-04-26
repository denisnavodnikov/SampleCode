package ru.navodnikov.denis.karagatantour.ui.island

import androidx.lifecycle.LiveData
import ru.navodnikov.denis.domain.entity.IslandEnum
import ru.navodnikov.denis.domain.entity.IslandScreen
import ru.navodnikov.denis.karagatantour.ui.base.FragmentContract

class IslandContract {
    interface ViewModel : FragmentContract.ViewModel {
        fun getIslandScreenLiveData(): LiveData<IslandScreen>
        fun selectedExcursions(island: IslandEnum)
        fun getselectedExcursionsLiveData(): LiveData<IslandViewModelState>
        fun selectedTransfers()
        fun selectedHotels(island: IslandEnum)
        fun getselectedTransfersLiveData(): LiveData<IslandViewModelState>
        fun getselectedHotelsLiveData(): LiveData<IslandViewModelState>
        fun clearNavigation()
    }

    interface View : FragmentContract.View {
        fun configureLiveDataObservers()
    }
}