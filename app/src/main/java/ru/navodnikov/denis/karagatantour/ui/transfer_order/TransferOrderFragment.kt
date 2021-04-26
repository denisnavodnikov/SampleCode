package ru.navodnikov.denis.karagatantour.ui.transfer_order

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.navodnikov.denis.karagatantour.R
import ru.navodnikov.denis.karagatantour.databinding.FragmentTransferOrderBinding
import ru.navodnikov.denis.karagatantour.ui.base.BaseFragment
import ru.navodnikov.denis.karagatantour.ui.utils.Constants.Companion.EMPTY_TEXT
import java.util.*


class TransferOrderFragment : BaseFragment<FragmentTransferOrderBinding>(),
    TransferOrderContract.View, DatePickerDialog.OnDateSetListener {

    private val transferOrderViewModel: TransferOrderViewModel by viewModel()


    override fun onResume() {
        super.onResume()
        configureLiveDataObservers()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentBinding = FragmentTransferOrderBinding.inflate(inflater, container, false)
        return fragmentBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentBinding?.transferIslandFromAutoComplete?.onItemClickListener =
            AdapterView.OnItemClickListener { parent,
                                              view, position,
                                              id ->
                run {
                    fragmentBinding?.transferCityFromAutoComplete?.setText(EMPTY_TEXT)
                    transferOrderViewModel.getListOfCitesFrom(position)
                }
            }

        fragmentBinding?.transferIslandToAutoComplete?.onItemClickListener =
            AdapterView.OnItemClickListener { parent,
                                              view, position,
                                              id ->
                run {
                    fragmentBinding?.transferCityToAutoComplete?.setText(EMPTY_TEXT)
                    transferOrderViewModel.getListOfCitesTo(position)
                }
            }
        fragmentBinding?.dateTransferBookEditText?.setOnClickListener {
            showDatePickerDialog()
        }
        fragmentBinding?.orderTransferFab?.setOnClickListener {
            val numberAdults = fragmentBinding?.numberAdultButton?.number?.toInt() ?: 0
            val numberChildren = fragmentBinding?.numberChildrenButton?.number?.toInt() ?: 0
            val date = fragmentBinding?.dateTransferBookEditText?.text.toString()
            val cityFrom = fragmentBinding?.transferCityFromAutoComplete?.text.toString()
            val cityTo = fragmentBinding?.transferCityToAutoComplete?.text.toString()
            val comments = fragmentBinding?.transferWishesAutoComplete?.text.toString()

            transferOrderViewModel.doOrder(
                cityFrom = cityFrom,
                cityTo = cityTo,
                numberAdults = numberAdults,
                numberChildren = numberChildren,
                date = date,
                comments = comments
            )
        }
    }

    override fun configureLiveDataObservers() {
        transferOrderViewModel.getIslandsLiveData().observe(this, { listOfIslands ->
            listOfIslands?.let {
                val arrayAdapter = context?.let { context ->
                    ArrayAdapter(context, R.layout.dropdown_item, listOfIslands)
                }
                fragmentBinding?.transferIslandFromAutoComplete?.setAdapter(arrayAdapter)
                fragmentBinding?.transferIslandToAutoComplete?.setAdapter(arrayAdapter)
            }

        })
        transferOrderViewModel.getCitesFromLiveData().observe(this, { listOfCites ->
            listOfCites?.let {
                val arrayAdapter = context?.let { context ->
                    ArrayAdapter(context, R.layout.dropdown_item, listOfCites)
                }
                fragmentBinding?.transferCityFromAutoComplete?.setAdapter(arrayAdapter)
            }
        })
        transferOrderViewModel.getCitesToLiveData().observe(this, { listOfCites ->
            listOfCites?.let {
                val arrayAdapter = context?.let { context ->
                    ArrayAdapter(context, R.layout.dropdown_item, listOfCites)
                }
                fragmentBinding?.transferCityToAutoComplete?.setAdapter(arrayAdapter)
            }
        })
        transferOrderViewModel?.getDataLiveData().observe(this, { date ->
            date?.let {
                fragmentBinding?.dateTransferBookEditText?.setText(date)
            }
        })
        transferOrderViewModel.getMassageLiveData().observe(this, {
            showMessage(it)
        })
    }

    override fun showDatePickerDialog() {
        val datePickerDialog: DatePickerDialog? = context?.let {
            DatePickerDialog(
                it,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
            )
        }
        datePickerDialog?.show()
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        var monthDate: String = month.toString()
        var dayOfMonthDate: String = dayOfMonth.toString()
        if (month < 10) {
            monthDate = "0$month"
        }
        if (dayOfMonth < 10) {
            dayOfMonthDate = "0$dayOfMonth"
        }
        transferOrderViewModel.setDateSince("$dayOfMonthDate.$monthDate.$year")
    }
}