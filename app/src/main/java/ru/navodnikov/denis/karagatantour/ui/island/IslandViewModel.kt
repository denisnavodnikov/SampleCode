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

    private val selectedTransfersMutableLiveData = MutableLiveData<IslandViewModelState>()
    private val selectedExcursionsMutableLiveData = MutableLiveData<IslandViewModelState>()
    private val selectedHotelsMutableLiveData = MutableLiveData<IslandViewModelState>()

    private val selectedTransfersLiveData : LiveData<IslandViewModelState> = selectedTransfersMutableLiveData
    private val selectedExcursionsLiveData: LiveData<IslandViewModelState> = selectedExcursionsMutableLiveData
    private val selectedHotelsLiveData: LiveData<IslandViewModelState> = selectedHotelsMutableLiveData

    override fun getIslandScreenLiveData(): LiveData<IslandScreen> = islandScreenLiveData
    override fun getselectedTransfersLiveData(): LiveData<IslandViewModelState> = selectedTransfersLiveData
    override fun getselectedExcursionsLiveData(): LiveData<IslandViewModelState> = selectedExcursionsLiveData
    override fun getselectedHotelsLiveData(): LiveData<IslandViewModelState> = selectedHotelsLiveData

    override fun selectedTransfers() {
        selectedTransfersMutableLiveData.value = IslandViewModelState.NavigateToTransfers(TRANSFERS)
    }

    override fun selectedExcursions(island: IslandEnum) {
        selectedExcursionsMutableLiveData.value = IslandViewModelState.NavigateToExcursions(island)
    }

    override fun selectedHotels(island: IslandEnum) {
        selectedHotelsMutableLiveData.value = IslandViewModelState.NavigateToHotels(island)
    }

    override fun clearNavigation() {
        selectedTransfersMutableLiveData.value = IslandViewModelState.None
        selectedExcursionsMutableLiveData.value = IslandViewModelState.None
        selectedHotelsMutableLiveData.value = IslandViewModelState.None
    }
}

sealed class IslandViewModelState {
    object None : IslandViewModelState()
    data class NavigateToExcursions(val island: IslandEnum) : IslandViewModelState()
    data class NavigateToTransfers(val destination: String) : IslandViewModelState()
    data class NavigateToHotels(val island: IslandEnum) : IslandViewModelState()
}