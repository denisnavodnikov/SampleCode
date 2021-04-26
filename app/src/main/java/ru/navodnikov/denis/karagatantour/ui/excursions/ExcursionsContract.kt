package ru.navodnikov.denis.karagatantour.ui.excursions

import androidx.lifecycle.LiveData
import ru.navodnikov.denis.domain.entity.Excursion
import ru.navodnikov.denis.karagatantour.ui.base.FragmentContract

class ExcursionsContract {
    interface ViewModel : FragmentContract.ViewModel {
        fun getExcursionsLiveData(): LiveData<List<Excursion>>
        fun selectExcursion(excursion: Excursion)
        fun getChangeExcursionLiveData(): LiveData<ExcursionsViewModelState>
        fun clearNavigation()
    }

    interface View : FragmentContract.View {
        fun configureLiveDataObservers()
    }
}