package com.example.spacexchallenge.ui.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.spacexchallenge.ui.theme.Sizes
import com.example.spacexchallenge.ui.theme.SpaceXChallengeTheme

@Composable
fun LoadingState() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(
                    size = Sizes.LoadingProgressSize
                )
        )
    }
}

@Composable
fun ErrorState(
    message: String,
    actionMessage: String = "try",
    action: (() -> Unit)? = null
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color.Red
            ).padding(
                horizontal = Sizes.itemHorizontalMargin,
                vertical = Sizes.itemVerticalMargin
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = message,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.displayMedium.copy(
                color = Color.White
            )
        )
//        Text(
//            text = String(Character.toChars(0x1F62D))
//        )
        action?.let {
            TextButton(
                onClick = { it.invoke() }
            ) {
                Text(
                    text = actionMessage,
                    style = MaterialTheme.typography.displaySmall.copy(
                        color = Color.Blue
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Preview() {
    SpaceXChallengeTheme {
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
        ) {
            ErrorState("Something gone wrong") {

            }
        }
    }
}