package ru.navodnikov.denis.karagatantour.ui.excursion

import androidx.lifecycle.LiveData
import ru.navodnikov.denis.domain.entity.ExcursionDomain
import ru.navodnikov.denis.karagatantour.entity.Excursion
import ru.navodnikov.denis.karagatantour.ui.base.FragmentContract

class ExcursionContract {
    interface ViewModel : FragmentContract.ViewModel {
        fun getDateLiveData(): LiveData<String>
        fun setDate(date: String)
        fun getExcursionLiveData(): LiveData<Excursion>
        fun setPrice(adults: Int, children: Int)
        fun getMassageLiveData(): LiveData<Int>
        fun getPriceLiveData(): LiveData<Int>
        fun doOrder(excursion: Excursion, numberAdults: Int, numberChildren: Int, date: String, price: String)
    }

    interface View : FragmentContract.View {
        fun configureLiveDataObservers()
        fun showDatePickerDialog()
    }
}