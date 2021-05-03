package ru.navodnikov.denis.karagatantour.ui.island

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.navodnikov.denis.domain.entity.IslandEnum
import ru.navodnikov.denis.domain.entity.IslandScreen
import ru.navodnikov.denis.domain.usecases.GetIslandScreenUseCase
import ru.navodnikov.denis.karagatantour.ui.utils.Constants.Companion.TRANSFERS

class IslandViewModel(private val getIslandScreenUseCase: GetIslandScreenUseCase, private val island: IslandEnum) :
    ViewModel(), IslandContract.ViewModel {

    private val islandScreenMutableLiveData: MutableLiveData<IslandScreen> =
        MutableLiveData(getIslandScreenUseCase.execute(island))
    private val islandScreenLiveData: LiveData<IslandScreen> = islandScreenMutableLiveData

    private val selectedTransfersLiveData = MutableLiveData<IslandViewModelState>()

    private val selectedExcursionsLiveData = MutableLiveData<IslandViewModelState>()

    private val selectedHotelsLiveData = MutableLiveData<IslandViewModelState>()

    override fun getIslandScreenLiveData(): LiveData<IslandScreen> = islandScreenLiveData
    override fun getSelectedTransfersLiveData(): LiveData<IslandViewModelState> = selectedTransfersLiveData
    override fun getSelectedExcursionsLiveData(): LiveData<IslandViewModelState> = selectedExcursionsLiveData
    override fun getSelectedHotelsLiveData(): LiveData<IslandViewModelState> = selectedHotelsLiveData

    override fun selectedTransfers() {
        selectedTransfersLiveData.value = IslandViewModelState.NavigateToTransfers(TRANSFERS)
    }

    override fun selectedExcursions(island: IslandEnum) {
        selectedExcursionsLiveData.value = IslandViewModelState.NavigateToExcursions(island)
    }

    override fun selectedHotels(island: IslandEnum) {
        selectedHotelsLiveData.value = IslandViewModelState.NavigateToHotels(island)
    }

    override fun clearNavigation() {
        selectedTransfersLiveData.value = IslandViewModelState.None
        selectedExcursionsLiveData.value = IslandViewModelState.None
        selectedHotelsLiveData.value = IslandViewModelState.None
    }
}

sealed class IslandViewModelState {
    object None : IslandViewModelState()
    data class NavigateToExcursions(val island: IslandEnum) : IslandViewModelState()
    data class NavigateToTransfers(val destination: String) : IslandViewModelState()
    data class NavigateToHotels(val island: IslandEnum) : IslandViewModelState()
}