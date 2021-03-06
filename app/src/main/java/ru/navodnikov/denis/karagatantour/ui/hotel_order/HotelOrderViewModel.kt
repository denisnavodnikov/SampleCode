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
    private val citesLiveData = MutableLiveData(getListOfCitesUseCase.execute(island.ordinal))
    private val messageLiveData = MutableLiveData<Int>()
    private val dateSinceLiveData = MutableLiveData<String>()
    private val dateBeforeMutableLiveData = MutableLiveData<String>()
    private val dateBeforeLiveData: LiveData<String> = dateBeforeMutableLiveData

    override fun getCitesLiveData() = citesLiveData
    override fun getDateSinceLiveData() = dateSinceLiveData
    override fun getDateBeforeLiveData() = dateBeforeLiveData
    override fun getMassageLiveData() = messageLiveData

    override fun setDateSince(date: String) {
        dateSinceLiveData.value = date
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
                messageLiveData.value = R.string.error_city_hotel
            }
            rating == ZERO -> {
                messageLiveData.value = R.string.error_rating
            }
            numberAdults == ZERO -> {
                messageLiveData.value = R.string.error_adults
            }
            dateSince.isEmpty() -> {
                messageLiveData.value = R.string.error_date_since
            }
            dateBefore.isEmpty() -> {
                messageLiveData.value = R.string.error_date_before
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
                        messageLiveData.value = R.string.hotel_add_to_cart
                    }
                }
            }
        }
    }
}