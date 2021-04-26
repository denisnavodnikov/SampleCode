package ru.navodnikov.denis.karagatantour.ui.start

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.navodnikov.denis.domain.entity.Island
import ru.navodnikov.denis.domain.entity.IslandEnum
import ru.navodnikov.denis.domain.usecases.GetListOfIslandsUseCase

class StartViewModel(private val getListOfIslandsUseCase: GetListOfIslandsUseCase) : ViewModel(), StartContract.ViewModel {

    private val islandsMutableLiveData = MutableLiveData(getListOfIslandsUseCase.execute())
    private val islandsLiveData: LiveData<List<Island>> = islandsMutableLiveData

    private val changeIslandMutableLiveData = MutableLiveData<StartViewModelState>()
    private val changeIslandLiveData: LiveData<StartViewModelState> = changeIslandMutableLiveData

    override fun getIslandsLiveData() = islandsLiveData
    override fun getChangeIslandLiveData() = changeIslandLiveData

    override fun selectIsland(island: IslandEnum) {
        changeIslandMutableLiveData.value = StartViewModelState.NavigateTo(island)
    }

    override fun clearNavigation(){
        changeIslandMutableLiveData.value = StartViewModelState.None
    }

}

sealed class StartViewModelState {
    object None : StartViewModelState()
    data class NavigateTo(val island: IslandEnum) : StartViewModelState()
}