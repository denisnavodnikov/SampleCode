package ru.navodnikov.denis.karagatantour.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.viewbinding.ViewBinding
import ru.navodnikov.denis.karagatantour.R

abstract class BaseFragment<BINDING : ViewBinding>: Fragment(),
    FragmentContract.View{

    protected var fragmentBinding: BINDING? = null

    protected var navController: NavController? = null

    open fun showMessage(error: Int) {
        (activity as MainActivity?)?.showMessage(error)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        navController = activity?.let { Navigation.findNavController(it, R.id.main_fragment) }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        fragmentBinding = null
    }
}

