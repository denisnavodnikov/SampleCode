package ru.navodnikov.denis.karagatantour.ui.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.navodnikov.denis.karagatantour.R
import ru.navodnikov.denis.karagatantour.databinding.FragmentOrderBinding
import ru.navodnikov.denis.karagatantour.ui.base.BaseFragment


class OrderFragment : BaseFragment<FragmentOrderBinding>(),
    OrderContract.View {

    private val orderViewModel: OrderViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentBinding = FragmentOrderBinding.inflate(inflater, container, false)
        return fragmentBinding?.root
    }

    override fun onResume() {
        super.onResume()
        configureLiveDataObservers()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentBinding?.orderButton?.setOnClickListener {
            val name = fragmentBinding?.nameAutoComplete?.text.toString()
            val phone = fragmentBinding?.telephoneAutoComplete?.text.toString()
            val email = fragmentBinding?.emailAutoComplete?.text.toString()
            val typeOfContact = fragmentBinding?.typesContactAutoComplete?.text.toString()
            orderViewModel.sendOrder(name = name, phone = phone, email = email, typeOfContact = typeOfContact)
        }
    }

    override fun configureLiveDataObservers() {
        orderViewModel.getTypesLiveData().observe(this, { listOfTypes ->
            listOfTypes?.let {
                val arrayAdapter = context?.let { context ->
                    ArrayAdapter(context, R.layout.dropdown_item, listOfTypes)
                }
                fragmentBinding?.typesContactAutoComplete?.setAdapter(arrayAdapter)
            }
        })
        orderViewModel.getMassageLiveData().observe(this, {
            showMessage(it)
        })
        orderViewModel.getNavigateLiveData().observe(this, { state ->
            state?.let {
                if (state is OrderViewModelState.NavigateToBasket) {
                    orderViewModel.clearNavigation()
                    navController?.navigate(OrderFragmentDirections.actionOrderFragmentToBasketFragment())
                }
            }
        })

    }
}