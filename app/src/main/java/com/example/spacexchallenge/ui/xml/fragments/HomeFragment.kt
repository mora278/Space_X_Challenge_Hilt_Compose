package com.example.spacexchallenge.ui.xml.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.spacexchallenge.databinding.FragmentHomeBinding
import com.example.spacexchallenge.ui.activities.MainActivity
import com.example.spacexchallenge.ui.viewmodels.HomeViewModel
import com.example.spacexchallenge.ui.xml.adapters.LaunchInfoBasicPagingDataAdapter
import com.example.spacexchallenge.ui.xml.utils.BindingAdapterUtils.showContentGroup
import com.example.spacexchallenge.ui.xml.utils.BindingAdapterUtils.showLoadingGroup
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: LaunchInfoBasicPagingDataAdapter
    private val homeViewModel: HomeViewModel by viewModels()
    private val navigateToLaunchDetails: ((launchId: String) -> Unit) = { launchId ->
        val currentActivity = activity
        if (currentActivity is MainActivity) {
            currentActivity.navigateToLaunchDetails(launchId)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this.viewLifecycleOwner
        binding.adapter =
            LaunchInfoBasicPagingDataAdapter(navigateToLaunchDetails).also { adapter = it }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.startCollectionData()
        lifecycleScope.launch {
            homeViewModel.homeUIState.collect { uiState ->
                binding.progressBar.showLoadingGroup(uiState)
                binding.recyclerView.showContentGroup(uiState)
                homeViewModel.launchesPagingData.collect {
                    adapter.submitData(it)
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()
        homeViewModel.stopCollectingData()
    }
}