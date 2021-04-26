package ru.navodnikov.denis.karagatantour.ui.basket

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import ru.navodnikov.denis.domain.entity.Order
import ru.navodnikov.denis.domain.usecases.DeleteOrderUseCase
import ru.navodnikov.denis.domain.usecases.GetListOfOrdersUseCase

class BasketViewModel(
    private val getListOfOrdersUseCase: GetListOfOrdersUseCase,
    private val deleteOrderUseCase: DeleteOrderUseCase
) : ViewModel(), BasketContract.ViewModel {

    private val ordersLiveData: LiveData<List<Order>> = getListOfOrdersUseCase.execute().asLiveData()

    private val massageMutableLiveData = MutableLiveData<Int>()
    private val massageLiveData: LiveData<Int> = massageMutableLiveData

    override fun getOrdersLiveData(): LiveData<List<Order>> = ordersLiveData
    override fun getMassageLiveData() = massageLiveData
    override fun deleteOrder(order: Order) {
        viewModelScope.launch {
            deleteOrderUseCase.execute(order)
        }
    }




}