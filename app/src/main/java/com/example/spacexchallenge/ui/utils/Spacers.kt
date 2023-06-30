package com.example.spacexchallenge.ui.utils

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.spacexchallenge.ui.theme.Sizes

@Composable
fun SpacerVerticalSmall() {
    Spacer(modifier = Modifier.size(height = Sizes.spacerVerticalSmall, width = Sizes.emptySize))
}

@Composable
fun SpacerVerticalMedium() {
    Spacer(modifier = Modifier.size(height = Sizes.spacerVerticalMedium, width = Sizes.emptySize))
}