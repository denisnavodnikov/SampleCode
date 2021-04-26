package ru.navodnikov.denis.karagatantour.ui.hotel_order

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.DatePicker
import androidx.navigation.fragment.navArgs
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import ru.navodnikov.denis.domain.entity.Date
import ru.navodnikov.denis.domain.entity.IslandEnum
import ru.navodnikov.denis.karagatantour.R
import ru.navodnikov.denis.karagatantour.databinding.FragmentHotelOrderBinding
import ru.navodnikov.denis.karagatantour.ui.base.BaseFragment
import java.util.*


class HotelOrderFragment : BaseFragment<FragmentHotelOrderBinding>(),
    HotelOrderContract.View, DatePickerDialog.OnDateSetListener {

    private val hotelOrderViewModel: HotelOrderViewModel by viewModel {
        parametersOf(currentIsland)
    }
    private val args: HotelOrderFragmentArgs by navArgs()
    private lateinit var currentIsland: IslandEnum
    private lateinit var date: Date

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        currentIsland = args.currentIsland
    }

    override fun onResume() {
        super.onResume()
        configureLiveDataObservers()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentBinding = FragmentHotelOrderBinding.inflate(inflater, container, false)
        return fragmentBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentBinding?.dateBeforeBookEditText?.setOnClickListener {
            showDatePickerDialog(Date.BEFORE)
        }

        fragmentBinding?.dateSinceBookEditText?.setOnClickListener {
            showDatePickerDialog(Date.SINCE)
        }
        fragmentBinding?.orderHotelFab?.setOnClickListener {
            val city = fragmentBinding?.hotelCitesAutoComplete?.text.toString()
            val rating = fragmentBinding?.ratingBar?.rating?.toInt() ?: 0
            val numberAdults = fragmentBinding?.numberAdultButton?.number?.toInt() ?: 0
            val numberChildren = fragmentBinding?.numberChildrenButton?.number?.toInt() ?: 0
            val dateSince = fragmentBinding?.dateSinceBookEditText?.text.toString()
            val dateBefore = fragmentBinding?.dateBeforeBookEditText?.text.toString()
            val comments = fragmentBinding?.hotelWishesAutoComplete?.text.toString()
            hotelOrderViewModel.doOrder(
                city = city,
                rating = rating,
                numberAdults = numberAdults,
                numberChildren = numberChildren,
                dateSince = dateSince,
                dateBefore = dateBefore,
                comments = comments
            )
        }
    }

    override fun configureLiveDataObservers() {
        hotelOrderViewModel.getCitesLiveData().observe(this, { arrayOfCites ->
            arrayOfCites?.let {
                val arrayAdapter = context?.let { context ->
                    ArrayAdapter(context, R.layout.dropdown_item, arrayOfCites)
                }
                fragmentBinding?.hotelCitesAutoComplete?.setAdapter(arrayAdapter)
            }
        })
        hotelOrderViewModel.getdateSinceLiveData().observe(this, { date ->
            date?.let {
                fragmentBinding?.dateSinceBookEditText?.setText(date)
            }
        })
        hotelOrderViewModel.getdateBeforeLiveData().observe(this, { date ->
            date?.let {
                fragmentBinding?.dateBeforeBookEditText?.setText(date)
            }
        })
        hotelOrderViewModel.getMassageLiveData().observe(this, {
            showMessage(it)
        })

    }

    override fun showDatePickerDialog(date: Date) {
        val datePickerDialog: DatePickerDialog? = context?.let {
            DatePickerDialog(
                it,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
            )
        }
        this.date = date
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
        when (date) {
            Date.SINCE -> hotelOrderViewModel.setDateSince("$dayOfMonthDate.$monthDate.$year")
            Date.BEFORE -> hotelOrderViewModel.setDateBefore("$dayOfMonthDate.$monthDate.$year")
        }
    }
}