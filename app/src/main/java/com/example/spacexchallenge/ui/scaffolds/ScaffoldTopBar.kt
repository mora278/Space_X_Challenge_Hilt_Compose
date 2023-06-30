package com.example.spacexchallenge.ui.scaffolds

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.spacexchallenge.R
import com.example.spacexchallenge.ui.theme.SpaceXChallengeTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldTopBarWithBack(
    onClickBack: () -> Unit,
    content: @Composable ((PaddingValues) -> Unit)
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = getTopBar(onClickBack = onClickBack),
        content = content
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun getTopBar(
    onClickBack: () -> Unit
): @Composable (() -> Unit) = {
    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.back))
        },
        navigationIcon = {
            IconButton(
                onClick = onClickBack
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(id = R.string.back)
                )
            }
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Preview() {
    SpaceXChallengeTheme {
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
        ) {
            ScaffoldTopBarWithBack(
                onClickBack = {},
                content = {}
            )
        }
    }
}