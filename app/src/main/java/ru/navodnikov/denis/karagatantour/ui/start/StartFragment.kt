package ru.navodnikov.denis.karagatantour.ui.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.navodnikov.denis.karagatantour.databinding.FragmentStartBinding
import ru.navodnikov.denis.karagatantour.ui.base.BaseFragment


class StartFragment : BaseFragment<FragmentStartBinding>(),
    StartContract.View {

    private val startViewModel: StartViewModel by viewModel()
    private lateinit var islandRecyclerAdapter: IslandRecyclerAdapter

    override fun onResume() {
        super.onResume()
        configureLiveDataObservers()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentBinding = FragmentStartBinding.inflate(inflater, container, false)
        return fragmentBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initScreen()
    }

    override fun initScreen() {
        fragmentBinding?.listOfIslandsRv?.apply {
            layoutManager = LinearLayoutManager(activity)
            islandRecyclerAdapter = IslandRecyclerAdapter(
                IslandRecyclerAdapter.OnClickListenerIsland {
                    startViewModel.selectIsland(it.island)
                })
            adapter = islandRecyclerAdapter
        }
    }

    override fun configureLiveDataObservers() {
        startViewModel.getIslandsLiveData().observe(this, { listOfIslands ->
            listOfIslands?.let {
                islandRecyclerAdapter.setItems(listOfIslands)
            }
        })

        startViewModel.getChangeIslandLiveData().observe(this, { state ->
            state?.let {
                if(state is StartViewModelState.NavigateTo){
                    startViewModel.clearNavigation()
                    navController?.navigate(StartFragmentDirections.actionStartFragmentToIslandFragment(state.island))
                }

            }
        })
    }
}