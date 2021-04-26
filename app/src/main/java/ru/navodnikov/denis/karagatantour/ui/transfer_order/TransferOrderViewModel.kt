package ru.navodnikov.denis.karagatantour.ui.transfer_order

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.navodnikov.denis.data.entity.EMPTY_TEXT
import ru.navodnikov.denis.domain.usecases.GetArrayOfTitlesIslandsUseCase
import ru.navodnikov.denis.domain.usecases.GetListOfCitesUseCase
import ru.navodnikov.denis.domain.usecases.OrderTransferUseCase
import ru.navodnikov.denis.karagatantour.R

class TransferOrderViewModel(
    private val orderTransferUseCase: OrderTransferUseCase,
    private val getArrayOfTitlesIslandsUseCase: GetArrayOfTitlesIslandsUseCase,
    private val getListOfCitesUseCase: GetListOfCitesUseCase
) : ViewModel(), TransferOrderContract.ViewModel {

    private val islandsMutableLiveData = MutableLiveData(getArrayOfTitlesIslandsUseCase.execute())
    private val islandsLiveData: LiveData<Array<String>> = islandsMutableLiveData

    private val messageMutableLiveData = MutableLiveData<Int>()
    private val messageLiveData: LiveData<Int> = messageMutableLiveData

    private val citesFromMutableLiveData: MutableLiveData<Array<out String>> = MutableLiveData()
    private val citesFromLiveData: LiveData<Array<out String>> = citesFromMutableLiveData

    private val citesToMutableLiveData: MutableLiveData<Array<out String>> = MutableLiveData()
    private val citesToLiveData: LiveData<Array<out String>> = citesToMutableLiveData

    private val dateMutableLiveData = MutableLiveData<String>()
    private val dateLiveData: LiveData<String> = dateMutableLiveData

    override fun getIslandsLiveData() = islandsLiveData
    override fun getCitesFromLiveData() = citesFromLiveData
    override fun getCitesToLiveData() = citesToLiveData
    override fun getMassageLiveData() = messageLiveData

    override fun setDateSince(date: String) {
        dateMutableLiveData.value = date
    }

    override fun getDataLiveData() = dateLiveData
    override fun doOrder(
        cityFrom: String,
        cityTo: String,
        numberAdults: Int,
        numberChildren: Int,
        date: String,
        comments: String
    ) {
        when {
            cityFrom == EMPTY_TEXT -> {
                messageMutableLiveData.value = R.string.error_city_from
            }
            cityTo == EMPTY_TEXT -> {
                messageMutableLiveData.value = R.string.error_city_to
            }
            numberAdults == 0 -> {
                messageMutableLiveData.value = R.string.error_adults
            }
            date == EMPTY_TEXT -> {
                messageMutableLiveData.value = R.string.error_date
            }
            else -> {
                viewModelScope.launch {
                    orderTransferUseCase.execute(cityFrom, cityTo, numberAdults, numberChildren, date, comments)
                    withContext(Dispatchers.Main) {
                        messageMutableLiveData.value = R.string.transfer_add_to_cart
                    }
                }

            }
        }
    }

    override fun getListOfCitesFrom(island: Int) {
        citesFromMutableLiveData.value = getListOfCitesUseCase.execute(island)
    }

    override fun getListOfCitesTo(island: Int) {
        citesToMutableLiveData.value = getListOfCitesUseCase.execute(island)
    }
}