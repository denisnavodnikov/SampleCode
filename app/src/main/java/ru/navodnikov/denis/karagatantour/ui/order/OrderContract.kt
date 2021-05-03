package ru.navodnikov.denis.karagatantour.ui.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.navodnikov.denis.karagatantour.ui.base.FragmentContract

class OrderContract {
    interface ViewModel : FragmentContract.ViewModel {
        fun getTypesLiveData(): LiveData<Array<String>>
        fun getMassageLiveData(): LiveData<Int>
        fun sendOrder(name: String, phone: String, email: String, typeOfContact: String)
        fun getNavigateLiveData(): LiveData<OrderViewModelState>
        fun clearNavigation()
    }
    interface View : FragmentContract.View {
        fun configureLiveDataObservers()
    }
}