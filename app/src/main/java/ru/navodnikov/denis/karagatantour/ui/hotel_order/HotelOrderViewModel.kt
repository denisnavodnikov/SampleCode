package ru.navodnikov.denis.karagatantour.ui.hotel_order

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.navodnikov.denis.domain.entity.IslandEnum
import ru.navodnikov.denis.domain.usecases.GetListOfCitesUseCase
import ru.navodnikov.denis.domain.usecases.OrderHotelUseCase
import ru.navodnikov.denis.karagatantour.R
import ru.navodnikov.denis.karagatantour.ui.utils.Constants.Companion.ZERO

class HotelOrderViewModel(
    private val orderHotelUseCase: OrderHotelUseCase,
    private val getListOfCitesUseCase: GetListOfCitesUseCase,
    private val island: IslandEnum
) :
    ViewModel(), HotelOrderContract.ViewModel {

    private val citesMutableLiveData = MutableLiveData(getListOfCitesUseCase.execute(island.ordinal))
    private val citesLiveData: LiveData<Array<out String>> = citesMutableLiveData

    private val messageMutableLiveData = MutableLiveData<Int>()
    private val messageLiveData: LiveData<Int> = messageMutableLiveData

    private val dateSinceMutableLiveData = MutableLiveData<String>()
    private val dateSinceLiveData: LiveData<String> = dateSinceMutableLiveData

    private val dateBeforeMutableLiveData = MutableLiveData<String>()
    private val dateBeforeLiveData: LiveData<String> = dateBeforeMutableLiveData

    override fun getCitesLiveData() = citesLiveData
    override fun getdateSinceLiveData() = dateSinceLiveData
    override fun getdateBeforeLiveData() = dateBeforeLiveData
    override fun getMassageLiveData() = messageLiveData

    override fun setDateSince(date: String) {
        dateSinceMutableLiveData.value = date
    }

    override fun setDateBefore(date: String) {
        dateBeforeMutableLiveData.value = date
    }

    override fun doOrder(
        city: String,
        rating: Int,
        numberAdults: Int,
        numberChildren: Int,
        dateSince: String,
        dateBefore: String,
        comments: String
    ) {
        when {
            city.isEmpty() -> {
                messageMutableLiveData.value = R.string.error_city_hotel
            }
            rating == ZERO -> {
                messageMutableLiveData.value = R.string.error_rating
            }
            numberAdults == ZERO -> {
                messageMutableLiveData.value = R.string.error_adults
            }
            dateSince.isEmpty() -> {
                messageMutableLiveData.value = R.string.error_date_since
            }
            dateBefore.isEmpty() -> {
                messageMutableLiveData.value = R.string.error_date_before
            }
            else -> {
                viewModelScope.launch {
                    orderHotelUseCase.execute(
                        city = city,
                        rating = rating,
                        numberAdults = numberAdults,
                        numberChildren = numberChildren,
                        dateSince = dateSince,
                        dateBefore = dateBefore,
                        comments = comments
                    )
                    withContext(Dispatchers.Main) {
                        messageMutableLiveData.value = R.string.hotel_add_to_cart
                    }
                }

            }
        }
    }
}