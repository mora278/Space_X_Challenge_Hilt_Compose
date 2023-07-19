package com.example.spacexchallenge.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.spacexchallenge.domain.models.LaunchInfo
import com.example.spacexchallenge.domain.models.UIState
import com.example.spacexchallenge.ui.components.ErrorState
import com.example.spacexchallenge.ui.components.LoadingState
import com.example.spacexchallenge.ui.components.ItemBasicInfoCardFormat
import com.example.spacexchallenge.ui.viewmodels.HomeViewModel

@Composable
fun HomeScreens(
    homeViewModel: HomeViewModel = hiltViewModel(),
    onClick: (launchId: String) -> Unit
) {
    val homeUIStates = homeViewModel.homeUIState.collectAsState()
    when (val currentState = homeUIStates.value) {
        UIState.Loading -> {
            homeViewModel.startCollectionData()
            LoadingState()
        }

        is UIState.Error -> {
            homeViewModel.stopCollectingData()
            ErrorState(message = currentState.message)
        }

        is UIState.Success -> HomeViewPaging(
            launches = currentState.data.collectAsLazyPagingItems(),
            onClick = onClick
        )
    }
}

@Composable
private fun HomeViewPaging(
    launches: LazyPagingItems<LaunchInfo>,
    onClick: (launchId: String) -> Unit
) {
    when (launches.loadState.refresh) {
        LoadState.Loading -> LoadingState()
        is LoadState.Error -> {}
        is LoadState.NotLoading -> {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                showLaunches(launches = launches, onClick = onClick)
                loadStateForAppend(append = launches.loadState.append)
            }
        }
    }
}

private fun LazyListScope.showLaunches(
    launches: LazyPagingItems<LaunchInfo>,
    onClick: (launchId: String) -> Unit
) {
    items(
        items = launches.itemSnapshotList
    ) { item ->
        item?.let { launch ->
            ItemBasicInfoCardFormat(
                title = launch.name,
                subtitle = launch.date,
                urlImage = launch.urlPatch,
                onClick = { onClick(launch.id) }
            )
        }
    }
}

private fun LazyListScope.loadStateForAppend(append: LoadState) {
    when (append) {
        is LoadState.NotLoading -> {}
        is LoadState.Error -> {}
        LoadState.Loading -> item {
            ItemBasicInfoCardFormat(
                title = "Loading...",
                subtitle = "More launches",
                urlImage = "",
                onClick = { }
            )
        }
    }
}