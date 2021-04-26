package ru.navodnikov.denis.karagatantour.ui.hotel_order

import androidx.lifecycle.LiveData
import ru.navodnikov.denis.domain.entity.Date
import ru.navodnikov.denis.karagatantour.ui.base.FragmentContract

class HotelOrderContract {
    interface ViewModel : FragmentContract.ViewModel {
        fun getCitesLiveData(): LiveData<Array<out String>>
        fun getdateSinceLiveData(): LiveData<String>
        fun getdateBeforeLiveData(): LiveData<String>
        fun setDateSince(date: String)
        fun setDateBefore(date: String)
        fun doOrder(city: String, rating: Int, numberAdults: Int, numberChildren: Int, dateSince: String, dateBefore: String, comments: String)
        fun getMassageLiveData(): LiveData<Int>
    }
    interface View : FragmentContract.View {
        fun configureLiveDataObservers()
        fun showDatePickerDialog(date: Date)
    }
}