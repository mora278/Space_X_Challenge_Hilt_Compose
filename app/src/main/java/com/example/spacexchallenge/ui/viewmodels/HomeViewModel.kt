package com.example.spacexchallenge.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.spacexchallenge.domain.models.LaunchInfo
import com.example.spacexchallenge.domain.usecases.GetHomeUseCase
import com.example.spacexchallenge.ui.states.HomeUIStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHomeUseCases: GetHomeUseCase
) : ViewModel() {
    private val _jobHomeUIState: Job
    private val _homeUIState: MutableStateFlow<HomeUIStates> =
        MutableStateFlow(HomeUIStates.Loading)
    val homeUIState: StateFlow<HomeUIStates> = _homeUIState

    private val _launchesPagingData: MutableStateFlow<PagingData<LaunchInfo>> =
        MutableStateFlow(PagingData.empty())

    init {
        _jobHomeUIState = viewModelScope.async { loadData() }
    }

    private suspend fun loadData() {
        _homeUIState.emit(HomeUIStates.Success(_launchesPagingData))
        getHomeUseCases.invoke().cachedIn(viewModelScope)
            .catch {
                it.message?.let { errorMessage -> _homeUIState.emit(HomeUIStates.Error(errorMessage)) }
            }.collect {
                _launchesPagingData.emit(it)
            }
    }

    fun startCollectionData() {
        _jobHomeUIState.start()
    }

    fun stopCollectingData() {
        _jobHomeUIState.cancel()
    }
}