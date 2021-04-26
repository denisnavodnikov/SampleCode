package ru.navodnikov.denis.karagatantour.ui.basket

import androidx.lifecycle.LiveData
import ru.navodnikov.denis.domain.entity.Order
import ru.navodnikov.denis.karagatantour.ui.base.FragmentContract

class BasketContract {
    interface ViewModel : FragmentContract.ViewModel {
        fun getOrdersLiveData(): LiveData<List<Order>>
        fun deleteOrder(order: Order)
        fun getMassageLiveData(): LiveData<Int>
    }
    interface View : FragmentContract.View {
        fun configureLiveDataObservers()
        fun initScreen()
    }
}