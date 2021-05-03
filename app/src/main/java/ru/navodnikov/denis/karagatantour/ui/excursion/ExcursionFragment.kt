package ru.navodnikov.denis.karagatantour.ui.excursion

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import ru.navodnikov.denis.karagatantour.R
import ru.navodnikov.denis.karagatantour.databinding.FragmentExcursionBinding
import ru.navodnikov.denis.karagatantour.entity.Excursion
import ru.navodnikov.denis.karagatantour.ui.base.BaseFragment
import java.io.File
import java.util.*


class ExcursionFragment : BaseFragment<FragmentExcursionBinding>(),
    ExcursionContract.View, DatePickerDialog.OnDateSetListener {

    private val excursionViewModel: ExcursionViewModel by viewModel { parametersOf(currentExcursion) }
    private val args: ExcursionFragmentArgs by navArgs()
    private lateinit var currentExcursion: Excursion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        currentExcursion = args.excursion
    }

    override fun onResume() {
        super.onResume()
        configureLiveDataObservers()
        fragmentBinding?.toolbar?.title
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentBinding = FragmentExcursionBinding.inflate(inflater, container, false)
        return fragmentBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentBinding?.dateEditText?.setOnClickListener {
            showDatePickerDialog()
        }

        fragmentBinding?.numberAdultButton?.setOnValueChangeListener { view, oldValue, newValue ->
            val children = fragmentBinding?.numberChildrenButton?.number?.toInt() ?: 0
            excursionViewModel.setPrice(adults = newValue, children = children)
        }

        fragmentBinding?.numberChildrenButton?.setOnValueChangeListener { view, oldValue, newValue ->
            val adults = fragmentBinding?.numberAdultButton?.number?.toInt() ?: 0
            excursionViewModel.setPrice(adults = adults, children = newValue)
        }

        fragmentBinding?.collapsFab?.setOnClickListener {
            val numberAdults = fragmentBinding?.numberAdultButton?.number?.toInt() ?: 0
            val numberChildren = fragmentBinding?.numberChildrenButton?.number?.toInt() ?: 0
            val date = fragmentBinding?.dateEditText?.text.toString()
            val price = fragmentBinding?.priceText?.text.toString()
            val excursion = currentExcursion
            excursionViewModel.doOrder(
                excursion = excursion,
                numberAdults = numberAdults,
                numberChildren = numberChildren,
                date = date,
                price = price
            )
        }
    }

    override fun configureLiveDataObservers() {
        excursionViewModel.getExcursionLiveData().observe(this, { excursion ->
            excursion?.let {
                fragmentBinding?.collapsingToolbar?.title = resources.getString(excursion.title)
                Picasso.get().load(File(excursion.imageUri)).into(fragmentBinding?.callapsImage)
                fragmentBinding?.bodyTextView?.text = resources.getString(excursion.body)
            }
        })
        excursionViewModel.getDateLiveData().observe(this, { date ->
            date?.let {
                fragmentBinding?.dateEditText?.setText(date)
            }
        })
        excursionViewModel.getPriceLiveData().observe(this, {
            fragmentBinding?.priceText?.text = resources.getString(R.string.price_result, it)
        })
        excursionViewModel.getMassageLiveData().observe(this, {
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
        excursionViewModel.setDate("$dayOfMonthDate.$monthDate.$year")
    }
}