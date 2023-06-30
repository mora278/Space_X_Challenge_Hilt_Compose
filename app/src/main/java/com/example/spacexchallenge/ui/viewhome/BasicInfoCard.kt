package com.example.spacexchallenge.ui.viewhome

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.spacexchallenge.ui.theme.Sizes
import com.example.spacexchallenge.ui.theme.SpaceXChallengeTheme
import com.example.spacexchallenge.ui.utils.ImageFromUrlWithDefaultRocket
import com.example.spacexchallenge.ui.utils.TextForItemBodyMedium
import com.example.spacexchallenge.ui.utils.TextForItemTitleLarge

@Composable
fun ItemBasicInfoCardFormat(
    title: String,
    subtitle: String,
    urlImage: String,
    onClick: () -> Unit = {}
) {
    CardForItemBasicInfo(
        onClick = onClick
    ) {
        ItemBasicInfoPlainFormat(
            title = title,
            subtitle = subtitle,
            urlImage = urlImage
        )
    }
}

@Composable
fun ItemBasicInfoPlainFormat(
    title: String,
    subtitle: String,
    urlImage: String
) {
    RowForItemBasicInfo {
        IconPatchForItemBasicInfo(urlImage = urlImage)
        ColumnForItemBasicInfo {
            TextForItemTitleLarge(text = title)
            TextForItemBodyMedium(text = subtitle, color = Color.Magenta)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CardForItemBasicInfo(
    onClick: () -> Unit,
    content: (@Composable ColumnScope.() -> Unit)
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
            ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = Sizes.itemCardElevation
        ),
        onClick = onClick,
        content = content
    )
}

@Composable
private fun RowForItemBasicInfo(
    content: @Composable (RowScope.() -> Unit)
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(
                align = Alignment.CenterVertically
            )
            .padding(
                horizontal = Sizes.itemHorizontalMargin,
                vertical = Sizes.itemVerticalMargin
            ),
        verticalAlignment = Alignment.CenterVertically,
        content = content
    )
}

@Composable
private fun ColumnForItemBasicInfo(
    content: @Composable (ColumnScope.() -> Unit)
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        content = content
    )
}

@Composable
private fun IconPatchForItemBasicInfo(
    urlImage: String
) {
    var genericModifier = Modifier
        .size(size = Sizes.itemImageSize)
        .padding(end = Sizes.itemImageMargin)
    if (urlImage.isBlank()) {
        genericModifier = genericModifier.wrapContentHeight(align = Alignment.CenterVertically)
    }
    ImageFromUrlWithDefaultRocket(modifier = genericModifier, urlImage = urlImage)
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Preview() {
    SpaceXChallengeTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {

        }
    }
}