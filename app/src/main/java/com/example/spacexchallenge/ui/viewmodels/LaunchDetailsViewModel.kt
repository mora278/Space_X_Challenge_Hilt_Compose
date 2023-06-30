package com.example.spacexchallenge.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spacexchallenge.data.api.APIResult
import com.example.spacexchallenge.domain.usecases.GetLaunchDetailsUseCase
import com.example.spacexchallenge.domain.usecases.GetUriHandlerUseCase
import com.example.spacexchallenge.ui.states.LaunchDetailsUIStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

@HiltViewModel
class LaunchDetailsViewModel @Inject constructor(
    private val getLaunchDetailsUseCases: GetLaunchDetailsUseCase,
    private val getUriHandlerUseCases: GetUriHandlerUseCase
) : ViewModel() {
    private lateinit var _jobLaunchDetailsUIState: Job
    private val _launchDetailsUIState: MutableStateFlow<LaunchDetailsUIStates> =
        MutableStateFlow(LaunchDetailsUIStates.Loading)
    val launchDetailsUIState: StateFlow<LaunchDetailsUIStates> = _launchDetailsUIState

    fun loadData(launchId: String) {
        if (this::_jobLaunchDetailsUIState.isInitialized.not()) {
            _jobLaunchDetailsUIState = viewModelScope.async {
                getLaunchDetailsUseCases.invoke(launchId).catch { error ->
                    val message = error.message ?: APIResult.ERROR_GENERIC
                    _launchDetailsUIState.emit(LaunchDetailsUIStates.Error(message))
                }.collect { launch ->
                    _launchDetailsUIState.emit(LaunchDetailsUIStates.Success(launch))
                }
            }
        }
        reloadData()
    }

    fun reloadData() {
        _jobLaunchDetailsUIState.start()
    }

    fun stopCollectingData() {
        _jobLaunchDetailsUIState.cancel()
    }

    fun openWebFromURI(uri: String) = getUriHandlerUseCases.invoke(uri)
}