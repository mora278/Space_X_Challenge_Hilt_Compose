package com.example.spacexchallenge.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.spacexchallenge.ui.components.theme.SpaceXChallengeTheme

@Composable
fun LaunchDetailsHeaderCard(
    name: String,
    date: String,
    urlPatch: String,
    urlBackground: String
) {
    BackgroundScaleCard(
        urlBackground = urlBackground
    ) {
        ItemBasicInfoPlainFormat(
            title = name,
            subtitle = date,
            urlImage = urlPatch
        )
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
            LaunchDetailsHeaderCard(
                name = "launchModel.name",
                date = "launchModel.date",
                urlPatch = "",
                urlBackground = "https://live.staticflickr.com/65535/49956396262_ef41c1d9b0_o.jpg"
            )
        }
    }
}