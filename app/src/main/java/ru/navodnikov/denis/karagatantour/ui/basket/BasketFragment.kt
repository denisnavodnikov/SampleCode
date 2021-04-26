package ru.navodnikov.denis.karagatantour.ui.basket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.navodnikov.denis.karagatantour.databinding.FragmentBasketBinding
import ru.navodnikov.denis.karagatantour.ui.base.BaseFragment
import ru.navodnikov.denis.karagatantour.ui.basket.helpers.SwipeToDeleteCallback


class BasketFragment : BaseFragment<FragmentBasketBinding>(),
    BasketContract.View {

    private val basketViewModel: BasketViewModel by viewModel()
    private lateinit var orderRecyclerAdapter: OrderRecyclerAdapter

    override fun onResume() {
        super.onResume()
        configureLiveDataObservers()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentBinding = FragmentBasketBinding.inflate(inflater, container, false)
        return fragmentBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initScreen()
        fragmentBinding?.prepareOrderButton?.setOnClickListener {
            navController?.navigate(BasketFragmentDirections.actionBasketFragmentToOrderFragment())
        }
    }

    override fun initScreen(){
        fragmentBinding?.listOfOrdersRv?.apply{
            layoutManager = LinearLayoutManager(activity)
            orderRecyclerAdapter = OrderRecyclerAdapter()
            adapter = orderRecyclerAdapter
        }

        val itemTouchHelper = context?.let {
            ItemTouchHelper(object : SwipeToDeleteCallback(it) {
                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    basketViewModel.deleteOrder(orderRecyclerAdapter.getOrderByPosition(viewHolder.adapterPosition))
                }
            })
        }
        itemTouchHelper?.attachToRecyclerView(fragmentBinding?.listOfOrdersRv)

    }

    override fun configureLiveDataObservers() {
        basketViewModel.getOrdersLiveData().observe(this, { listOfOrders ->
            listOfOrders?.let {
                if (listOfOrders.isNotEmpty()){
                    fragmentBinding?.emptyCartTextView?.visibility = GONE
                    fragmentBinding?.emptyCartImageView?.visibility = GONE
                    fragmentBinding?.listOfOrdersRv?.visibility = VISIBLE
                    fragmentBinding?.prepareOrderButton?.visibility = VISIBLE
                }else{
                    fragmentBinding?.emptyCartTextView?.visibility = VISIBLE
                    fragmentBinding?.emptyCartImageView?.visibility = VISIBLE
                    fragmentBinding?.listOfOrdersRv?.visibility = GONE
                    fragmentBinding?.prepareOrderButton?.visibility = GONE
                }
                orderRecyclerAdapter.setItems(listOfOrders)
            }
        })
        basketViewModel.getMassageLiveData().observe(this, {
            showMessage(it)
        })
    }
}