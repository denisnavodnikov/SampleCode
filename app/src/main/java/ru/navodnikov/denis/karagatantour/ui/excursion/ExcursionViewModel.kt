package ru.navodnikov.denis.karagatantour.ui.excursion

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.navodnikov.denis.data.entity.EMPTY_TEXT
import ru.navodnikov.denis.domain.entity.Excursion
import ru.navodnikov.denis.domain.usecases.OrderExcursionUseCase
import ru.navodnikov.denis.karagatantour.R
import ru.navodnikov.denis.karagatantour.ui.utils.countPrice

class ExcursionViewModel(
    private val orderExcursionUseCase: OrderExcursionUseCase,
    private val excursion: Excursion
) : ViewModel(), ExcursionContract.ViewModel {

    private val excursionLiveData = MutableLiveData(excursion)
    private val dateLiveData = MutableLiveData<String>()
    private val massageLiveData = MutableLiveData<Int>()
    private val priceLiveData = MutableLiveData<Int>()

    override fun getExcursionLiveData() = excursionLiveData
    override fun getDateLiveData() = dateLiveData
    override fun getMassageLiveData() = massageLiveData
    override fun getPriceLiveData() = priceLiveData

    override fun setPrice(adults: Int, children: Int) {
        if (adults == 0 && children > 0) {
            massageLiveData.value = R.string.error_price_less
        } else {
            priceLiveData.value = countPrice(adults, children, excursion)
        }
    }

    override fun doOrder(
        excursion: Excursion,
        numberAdults: Int,
        numberChildren: Int,
        date: String,
        price: String
    ) {

        when {
            numberAdults == 0 -> {
                massageLiveData.value = R.string.error_adults
            }
            date == EMPTY_TEXT -> {
                massageLiveData.value = R.string.error_date
            }
            else -> {
                viewModelScope.launch {
                    orderExcursionUseCase.execute(
                        excursion,
                        numberAdults,
                        numberChildren,
                        date,
                        price
                    )
                    withContext(Dispatchers.Main) {
                        massageLiveData.value = R.string.excursion_add_to_cart
                    }
                }
            }
        }
    }

    override fun setDate(date: String) {
        dateLiveData.value = date
    }
}