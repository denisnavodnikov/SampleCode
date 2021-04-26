package ru.navodnikov.denis.karagatantour.ui.excursions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import ru.navodnikov.denis.domain.entity.IslandEnum
import ru.navodnikov.denis.karagatantour.databinding.FragmentExcursionsBinding
import ru.navodnikov.denis.karagatantour.ui.base.BaseFragment
import kotlin.math.abs


class ExcursionsFragment : BaseFragment<FragmentExcursionsBinding>(),
    ExcursionsContract.View {

    private val excursionsViewModel: ExcursionsViewModel by viewModel { parametersOf(currentIsland) }
    private val args: ExcursionsFragmentArgs by navArgs()
    private lateinit var currentIsland: IslandEnum
    private lateinit var excursionsAdapter: ExcursionsAdapter

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
        fragmentBinding = FragmentExcursionsBinding.inflate(inflater, container, false)
        return fragmentBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addCompositePageTransformer()
    }

    private fun addCompositePageTransformer() {
        excursionsAdapter = ExcursionsAdapter(ExcursionsAdapter.OnClickListenerExcursion { currentExcursion ->
            excursionsViewModel.selectExcursion(currentExcursion)
        })
        fragmentBinding?.excursionsViewPager?.adapter = excursionsAdapter

        fragmentBinding?.excursionsViewPager?.clipToPadding = false
        fragmentBinding?.excursionsViewPager?.clipChildren = false
        fragmentBinding?.excursionsViewPager?.offscreenPageLimit = 3
        fragmentBinding?.excursionsViewPager?.getChildAt(0)?.overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(10))
        compositePageTransformer.addTransformer(
            ViewPager2.PageTransformer { page: View, position: Float ->
                val r = 1 - abs(position)
                page.scaleY = 0.85f + r * 0.15f
            })
        fragmentBinding?.excursionsViewPager?.setPageTransformer(compositePageTransformer)
    }

    override fun configureLiveDataObservers() {
        excursionsViewModel.getExcursionsLiveData().observe(this, { listOfExcursions ->
            listOfExcursions?.let {
                excursionsAdapter.setItems(listOfExcursions)
            }
        })
        excursionsViewModel.getChangeExcursionLiveData().observe(this, { state ->
            state?.let {
                if (state is ExcursionsViewModelState.NavigateTo) {
                    excursionsViewModel.clearNavigation()
                    navController?.navigate(
                        ExcursionsFragmentDirections.actionExcursionsFragmentToExcursionFragment(state.excursion)
                    )
                }
            }
        })
    }

}