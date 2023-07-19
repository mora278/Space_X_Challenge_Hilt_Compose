package com.example.spacexchallenge.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.spacexchallenge.domain.models.LaunchInfo
import com.example.spacexchallenge.domain.models.UIState
import com.example.spacexchallenge.domain.usecases.GetHomeUseCase
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
    private val _homeUIState: MutableStateFlow<UIState<StateFlow<PagingData<LaunchInfo>>>> =
        MutableStateFlow(UIState.Loading)
    val homeUIState: StateFlow<UIState<StateFlow<PagingData<LaunchInfo>>>> = _homeUIState

    private val _launchesPagingData: MutableStateFlow<PagingData<LaunchInfo>> =
        MutableStateFlow(PagingData.empty())
    val launchesPagingData: StateFlow<PagingData<LaunchInfo>> = _launchesPagingData

    init {
        _jobHomeUIState = viewModelScope.async { loadData() }
    }

    private suspend fun loadData() {
        _homeUIState.emit(UIState.Success(_launchesPagingData))
        getHomeUseCases.invoke().cachedIn(viewModelScope)
            .catch {
                it.message?.let { errorMessage -> _homeUIState.emit(UIState.Error(errorMessage)) }
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