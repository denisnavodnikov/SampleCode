package ru.navodnikov.denis.karagatantour.ui.start

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.navodnikov.denis.domain.entity.IslandEnum
import ru.navodnikov.denis.domain.usecases.GetListOfIslandsUseCase

class StartViewModel(private val getListOfIslandsUseCase: GetListOfIslandsUseCase) : ViewModel(), StartContract.ViewModel {

    private val islandsLiveData = MutableLiveData(getListOfIslandsUseCase.execute())
    private val changeIslandLiveData = MutableLiveData<StartViewModelState>()

    override fun getIslandsLiveData() = islandsLiveData
    override fun getChangeIslandLiveData() = changeIslandLiveData

    override fun selectIsland(island: IslandEnum) {
        changeIslandLiveData.value = StartViewModelState.NavigateTo(island)
    }

    override fun clearNavigation(){
        changeIslandLiveData.value = StartViewModelState.None
    }
}

sealed class StartViewModelState {
    object None : StartViewModelState()
    data class NavigateTo(val island: IslandEnum) : StartViewModelState()
}