package ru.navodnikov.denis.karagatantour.ui.island

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import ru.navodnikov.denis.domain.entity.IslandEnum
import ru.navodnikov.denis.karagatantour.databinding.FragmentIslandBinding
import ru.navodnikov.denis.karagatantour.ui.base.BaseFragment
import java.io.File

class IslandFragment : BaseFragment<FragmentIslandBinding>(),
    IslandContract.View {

    private val islandViewModel: IslandViewModel by viewModel {
        parametersOf(currentIsland)
    }
    private val args: IslandFragmentArgs by navArgs()
    private lateinit var currentIsland: IslandEnum

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
        fragmentBinding = FragmentIslandBinding.inflate(inflater, container, false)
        return fragmentBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentBinding?.islandTransfersKbv?.setOnClickListener {
            islandViewModel.selectedTransfers()
        }
        fragmentBinding?.islandExcursionsKbv?.setOnClickListener {
            islandViewModel.selectedExcursions(currentIsland)
        }
        fragmentBinding?.islandHotelsKbv?.setOnClickListener {
            islandViewModel.selectedHotels(currentIsland)
        }
    }

    override fun configureLiveDataObservers() {
        islandViewModel.getIslandScreenLiveData().observe(this, { islandScreen ->
            islandScreen?.let {
                fragmentBinding?.islandTextView?.text = getText(islandScreen.title)
                Picasso.get().load(File(islandScreen.imageTransfer)).into(fragmentBinding?.islandTransfersKbv)
                Picasso.get().load(File(islandScreen.imageExcursion)).into(fragmentBinding?.islandExcursionsKbv)
                Picasso.get().load(File(islandScreen.imageHotel)).into(fragmentBinding?.islandHotelsKbv)
            }
        })
        islandViewModel.getSelectedTransfersLiveData().observe(this, { state ->
            state?.let {
                if (state is IslandViewModelState.NavigateToTransfers) {
                    islandViewModel.clearNavigation()
                    navController?.navigate(IslandFragmentDirections.actionIslandFragmentToTransferOrderFragment())
                }
            }
        })
        islandViewModel.getSelectedExcursionsLiveData().observe(this, { state ->
            state?.let {
                if (state is IslandViewModelState.NavigateToExcursions) {
                    islandViewModel.clearNavigation()
                    navController?.navigate(
                        IslandFragmentDirections.actionIslandFragmentToExcursionsFragment(state.island)
                    )
                }
            }
        })
        islandViewModel.getSelectedHotelsLiveData().observe(this, { state ->
            state?.let {
                if (state is IslandViewModelState.NavigateToHotels) {
                    islandViewModel.clearNavigation()
                    navController?.navigate(
                        IslandFragmentDirections.actionIslandFragmentToHotelOrderFragment(state.island)
                    )
                }
            }
        })
    }


}