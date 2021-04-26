package ru.navodnikov.denis.karagatantour.ui.transfer_order

import androidx.lifecycle.LiveData
import ru.navodnikov.denis.karagatantour.ui.base.FragmentContract

class TransferOrderContract {
    interface ViewModel : FragmentContract.ViewModel {
        fun getIslandsLiveData(): LiveData<Array<String>>
        fun getListOfCitesFrom(island: Int)
        fun getListOfCitesTo(island: Int)
        fun getCitesFromLiveData(): LiveData<Array<out String>>
        fun getCitesToLiveData(): LiveData<Array<out String>>
        fun setDateSince(date: String)
        fun getDataLiveData(): LiveData<String>
        fun doOrder(cityFrom: String, cityTo: String, numberAdults: Int, numberChildren: Int, date: String, comments: String)
        fun getMassageLiveData(): LiveData<Int>
    }

    interface View : FragmentContract.View {
        fun configureLiveDataObservers()
        fun showDatePickerDialog()
    }
}