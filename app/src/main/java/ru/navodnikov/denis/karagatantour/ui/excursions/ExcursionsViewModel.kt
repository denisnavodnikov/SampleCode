package ru.navodnikov.denis.karagatantour.ui.excursions

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.navodnikov.denis.domain.entity.IslandEnum
import ru.navodnikov.denis.domain.entity.mapToDomain
import ru.navodnikov.denis.domain.usecases.GetListOfExcursionsUseCase
import ru.navodnikov.denis.karagatantour.entity.Excursion

class ExcursionsViewModel(private val getListOfExcursionsUseCase: GetListOfExcursionsUseCase, private val island: IslandEnum) : ViewModel(), ExcursionsContract.ViewModel {

    private val excursionsLiveData = MutableLiveData(getListOfExcursionsUseCase.execute(island).map { it.mapToDomain() })
    private val changeExcursionLiveData  = MutableLiveData<ExcursionsViewModelState>()

    override fun getExcursionsLiveData()= excursionsLiveData
    override fun getChangeExcursionLiveData()= changeExcursionLiveData

    override fun selectExcursion(excursion: Excursion) {
        changeExcursionLiveData.value = ExcursionsViewModelState.NavigateTo(excursion)
    }

    override fun clearNavigation(){
        changeExcursionLiveData.value = ExcursionsViewModelState.None
    }

}

sealed class ExcursionsViewModelState {
    object None : ExcursionsViewModelState()
    data class NavigateTo(val excursion: Excursion) : ExcursionsViewModelState()
}

