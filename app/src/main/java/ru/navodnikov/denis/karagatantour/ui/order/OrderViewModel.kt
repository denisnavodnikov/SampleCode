package ru.navodnikov.denis.karagatantour.ui.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.navodnikov.denis.domain.usecases.DeleteOrderUseCase
import ru.navodnikov.denis.domain.usecases.GetListOfOrdersUseCase
import ru.navodnikov.denis.domain.usecases.GetTypesOfContacts
import ru.navodnikov.denis.domain.usecases.SendOrdersUseCase
import ru.navodnikov.denis.karagatantour.R

class OrderViewModel(
    private val getTypesOfContacts: GetTypesOfContacts,
    private val sendOrdersUseCase: SendOrdersUseCase,
    private val deleteOrderUseCase: DeleteOrderUseCase,
    private val getListOfOrdersUseCase: GetListOfOrdersUseCase
) : ViewModel(), OrderContract.ViewModel {

    private val navigateMutableLiveData = MutableLiveData<OrderViewModelState>()
    private val navigateLiveData = navigateMutableLiveData

    private val typesMutableLiveData = MutableLiveData(getTypesOfContacts.execute())
    private val typesLiveData: LiveData<Array<String>> = typesMutableLiveData

    private val messageMutableLiveData = MutableLiveData<Int>()
    private val messageLiveData: LiveData<Int> = messageMutableLiveData

    override fun getNavigateLiveData() = navigateLiveData

    override fun getMassageLiveData() = messageLiveData

    override fun getTypesLiveData() = typesLiveData

    override fun sendOrder(name: String, phone: String, email: String, typeOfContact: String) {
        when {
            name.isEmpty() -> {
                messageMutableLiveData.value = R.string.error_name
            }
            phone.isEmpty() -> {
                messageMutableLiveData.value = R.string.error_phone
            }
            email.isEmpty() -> {
                messageMutableLiveData.value = R.string.error_email
            }
            typeOfContact.isEmpty() -> {
                messageMutableLiveData.value = R.string.error_type
            }
            else -> {
                viewModelScope.launch {
                    val resultIsOk = sendOrdersUseCase.execute(
                        name = name,
                        phone = phone,
                        email = email,
                        typeOfContact = typeOfContact
                    )
                    if (resultIsOk) {
                        for (order in getListOfOrdersUseCase.execute().first())
                            deleteOrderUseCase.execute(order)
                        withContext(Dispatchers.Main) {
                            messageMutableLiveData.value = R.string.oder_is_sended
                            navigateMutableLiveData.value = OrderViewModelState.NavigateToBasket
                        }
                    }
                    try {

                    } catch (e: Exception) {
                        withContext(Dispatchers.Main) {
                            messageMutableLiveData.value = R.string.error_send_order
                        }
                    }


                }
            }
        }

    }
    override fun clearNavigation() {
        navigateMutableLiveData.value = OrderViewModelState.None
    }
}

sealed class OrderViewModelState {
    object None : OrderViewModelState()
    object NavigateToBasket : OrderViewModelState()
}