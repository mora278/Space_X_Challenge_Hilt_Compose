package com.example.spacexchallenge.ui.xml.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.spacexchallenge.databinding.FragmentLaunchDetailsBinding
import com.example.spacexchallenge.domain.models.UIState
import com.example.spacexchallenge.ui.activities.MainActivity
import com.example.spacexchallenge.ui.viewmodels.LaunchDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LaunchDetailsFragment : Fragment() {
    private lateinit var binding: FragmentLaunchDetailsBinding
    private val launchDetailsViewModel: LaunchDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLaunchDetailsBinding.inflate(inflater, )
        binding.launchDetailsViewModel = launchDetailsViewModel
        binding.lifecycleOwner = this.viewLifecycleOwner
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }
        return binding.root
    }

    override fun onStop() {
        super.onStop()
        launchDetailsViewModel.stopCollectingData()
    }

    private fun onBackPressed() {
        val currentActivity = activity
        if (currentActivity is MainActivity) {
            currentActivity.popBackStack()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val launchId = navArgs<LaunchDetailsFragmentArgs>().value.launchId
        launchDetailsViewModel.loadData(launchId)
        lifecycleScope.launch {
            launchDetailsViewModel.launchDetailsUIState.collect { uiState ->
                when (uiState) {
                    UIState.Loading -> {

                    }
                    is UIState.Error -> {
                        launchDetailsViewModel.reloadData()
                    }
                    is UIState.Success -> {
                        binding.launchInfo = uiState.data
                    }
                }
            }
        }
    }
}