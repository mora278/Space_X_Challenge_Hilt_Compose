package com.example.spacexchallenge.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.example.spacexchallenge.ui.components.theme.Sizes
import com.example.spacexchallenge.ui.components.theme.SpaceXChallengeTheme

@Composable
fun BackgroundScaleCard(
    urlBackground: String,
    content: @Composable (BoxScope.() -> Unit) = {}
) {
    CardDetails {
        BoxDetails(
            urlBackground = urlBackground
        ) {
            content()
        }
    }
}

@Composable
private fun CardDetails(
    content: @Composable (ColumnScope.() -> Unit)
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(
                align = Alignment.CenterVertically
            )
            .padding(
                start = Sizes.itemHorizontalMargin,
                top = Sizes.itemVerticalMargin,
                end = Sizes.itemHorizontalMargin
            )
            .aspectRatio(
                ratio = 1f
            ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = Sizes.itemCardElevation
        ),
        content = content
    )
}

@Composable
private fun BoxDetails(
    urlBackground: String,
    content: @Composable (BoxScope.() -> Unit)
) {
    Box {
        if (urlBackground.isNotBlank()) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                model = urlBackground,
                contentDescription = null
            )
        }
        content()
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Preview() {
    SpaceXChallengeTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            BackgroundScaleCard(
                urlBackground = "https://live.staticflickr.com/65535/49956396262_ef41c1d9b0_o.jpg"
            ) {

            }
        }
    }
}