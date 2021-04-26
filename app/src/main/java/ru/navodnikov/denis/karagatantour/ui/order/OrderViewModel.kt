package ru.navodnikov.denis.karagatantour.ui.order

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.navodnikov.denis.domain.usecases.DeleteOrderUseCase
import ru.navodnikov.denis.domain.usecases.GetListOfOrdersUseCase
import ru.navodnikov.denis.domain.usecases.GetTypesOfContacts
import ru.navodnikov.denis.domain.usecases.SendOrdersUseCase
import ru.navodnikov.denis.karagatantour.R

class OrderViewModel(
    private val getTypesOfContacts: GetTypesOfContacts,
    private val sendOrdersUseCase: SendOrdersUseCase
) : ViewModel(), OrderContract.ViewModel {
    private val navigateLiveData = MutableLiveData<OrderViewModelState>()
    private val typesLiveData = MutableLiveData(getTypesOfContacts.execute())
    private val messageLiveData = MutableLiveData<Int>()

    override fun getNavigateLiveData() = navigateLiveData
    override fun getMassageLiveData() = messageLiveData
    override fun getTypesLiveData() = typesLiveData

    override fun sendOrder(name: String, phone: String, email: String, typeOfContact: String) {
        when {
            name.isEmpty() -> {
                messageLiveData.value = R.string.error_name
            }
            phone.isEmpty() -> {
                messageLiveData.value = R.string.error_phone
            }
            email.isEmpty() -> {
                messageLiveData.value = R.string.error_email
            }
            typeOfContact.isEmpty() -> {
                messageLiveData.value = R.string.error_type
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
                        withContext(Dispatchers.Main) {
                            messageLiveData.value = R.string.oder_is_sended
                            navigateLiveData.value = OrderViewModelState.NavigateToBasket
                        }
                    }
                    try {

                    } catch (e: Exception) {
                        withContext(Dispatchers.Main) {
                            messageLiveData.value = R.string.error_send_order
                        }
                    }
                }
            }
        }
    }

    override fun clearNavigation() {
        navigateLiveData.value = OrderViewModelState.None
    }
}

sealed class OrderViewModelState {
    object None : OrderViewModelState()
    object NavigateToBasket : OrderViewModelState()
}