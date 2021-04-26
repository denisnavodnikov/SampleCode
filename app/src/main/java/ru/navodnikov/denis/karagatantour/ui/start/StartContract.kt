package ru.navodnikov.denis.karagatantour.ui.start

import androidx.lifecycle.LiveData
import ru.navodnikov.denis.domain.entity.Island
import ru.navodnikov.denis.domain.entity.IslandEnum
import ru.navodnikov.denis.karagatantour.ui.base.FragmentContract

class StartContract {
    interface ViewModel : FragmentContract.ViewModel {
        fun getIslandsLiveData(): LiveData<List<Island>>
        fun selectIsland(island: IslandEnum)
        fun getChangeIslandLiveData(): LiveData<StartViewModelState>
        fun clearNavigation()
    }

    interface View : FragmentContract.View {
        fun initScreen()
        fun configureLiveDataObservers()
    }
}