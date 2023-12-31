package com.example.spacexchallenge.ui.viewlaunchdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.spacexchallenge.R
import com.example.spacexchallenge.domain.models.LaunchInfo
import com.example.spacexchallenge.ui.scaffolds.ScaffoldTopBarWithBack
import com.example.spacexchallenge.ui.states.LaunchDetailsUIStates
import com.example.spacexchallenge.ui.theme.Sizes
import com.example.spacexchallenge.ui.theme.SpaceXChallengeTheme
import com.example.spacexchallenge.ui.utils.BackgroundScaleCard
import com.example.spacexchallenge.ui.utils.ErrorState
import com.example.spacexchallenge.ui.utils.LoadingState
import com.example.spacexchallenge.ui.utils.SpacerVerticalMedium
import com.example.spacexchallenge.ui.utils.SpacerVerticalSmall
import com.example.spacexchallenge.ui.utils.TextForClickableLink
import com.example.spacexchallenge.ui.utils.TextForItemBodyMedium
import com.example.spacexchallenge.ui.utils.TextForItemTitleLarge
import com.example.spacexchallenge.ui.viewmodels.LaunchDetailsViewModel

@Composable
fun LaunchDetailsPage(
    launchDetailsViewModel: LaunchDetailsViewModel = hiltViewModel(),
    launchId: String,
    onClickBack: () -> Unit
) {
    val launchDetailsUIState = launchDetailsViewModel.launchDetailsUIState.collectAsState()
    when (val currentState = launchDetailsUIState.value) {
        LaunchDetailsUIStates.Loading -> {
            LoadingState()
            launchDetailsViewModel.loadData(launchId)
        }

        is LaunchDetailsUIStates.Error -> {
            ErrorState(currentState.message) {
                launchDetailsViewModel.reloadData()
            }
        }

        is LaunchDetailsUIStates.Success -> {
            launchDetailsViewModel.stopCollectingData()
            LaunchDetailsViewSuccess(
                LaunchInfo = currentState.launch,
                onClickArticle = { launchDetailsViewModel.openWebFromURI(currentState.launch.urlArticle) },
                onClickBack = onClickBack
            )
        }
    }
}

@Composable
private fun LaunchDetailsViewSuccess(
    LaunchInfo: LaunchInfo,
    onClickArticle: () -> Unit,
    onClickBack: () -> Unit
) {
    ScaffoldTopBarWithBack(
        onClickBack = onClickBack
    ) {
        LazyColumnContent(
            paddingValues = it
        ) {
            item { ItemHeader(LaunchInfo = LaunchInfo) }
            item { ItemArticleLink(onClickArticle = onClickArticle) }
            item { SpacerVerticalMedium() }
            item { ItemDescription(description = LaunchInfo.details) }
            item { SpacerVerticalMedium() }
            item { ItemMediaText(urlPhotos = LaunchInfo.urlPhotos) }
            items(count = LaunchInfo.urlPhotos.count()) { index ->
                BackgroundScaleCard(urlBackground = LaunchInfo.urlPhotos[index])
            }
        }
    }
}

@Composable
fun LazyColumnContent(
    paddingValues: PaddingValues,
    content: (LazyListScope.() -> Unit)
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = paddingValues.calculateTopPadding()
            ),
        content = content
    )
}

@Composable
private fun ItemHeader(
    LaunchInfo: LaunchInfo
) {
    LaunchDetailsHeaderCard(
        name = LaunchInfo.name,
        date = LaunchInfo.date,
        urlPatch = LaunchInfo.urlPatch,
        urlBackground = LaunchInfo.urlPhotos.lastOrNull() ?: "https://free.toppng.com/uploads/preview/spacex-logo-11609377542pzdtc3f0hg.png"
    )
}

@Composable
private fun ItemArticleLink(
    onClickArticle: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = Sizes.itemHorizontalMargin
            ),
        horizontalAlignment = Alignment.End
    ) {
        TextForClickableLink(
            text = stringResource(id = R.string.open_article),
            linkColor = Color.Blue,
            onClick = onClickArticle
        )
    }
}

@Composable
private fun ItemDescription(
    description: String?
) {
    Column(
        modifier = Modifier
            .padding(
                horizontal = Sizes.itemHorizontalMargin
            )
    ) {
        val isDescriptionAvailable = description.isNullOrBlank().not()
        TextForItemTitleLarge(
            text = stringResource(id = if (isDescriptionAvailable) R.string.description else R.string.no_description),
            color = Color.Red
        )
        description?.let {
            SpacerVerticalSmall()
            TextForItemBodyMedium(text = description, maxLines = 4)
        }
    }
}

@Composable
private fun ItemMediaText(
    urlPhotos: List<String>
) {
    Column(
        modifier = Modifier
            .padding(
                horizontal = Sizes.itemHorizontalMargin
            )
    ) {
        val isMediaAvailable = urlPhotos.isNotEmpty()
        TextForItemTitleLarge(
            text = stringResource(id = if (isMediaAvailable) R.string.media else R.string.no_media),
            color = Color.Red
        )
        SpacerVerticalSmall()
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Preview() {
    SpaceXChallengeTheme {
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
        ) {

        }
    }
}