package com.example.spacexchallenge.ui.utils

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow


@Composable
fun TextForItemTitleLarge(
    text: String,
    color: Color = MaterialTheme.typography.titleLarge.color
) {
    Text(
        text = text,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.titleLarge.copy(
            color = color
        )
    )
}

@Composable
fun TextForItemBodyMedium(
    text: String,
    color: Color = MaterialTheme.typography.bodyMedium.color,
    maxLines: Int = 1
) {
    Text(
        text = text,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis,
        textAlign = TextAlign.Justify,
        style = MaterialTheme.typography.bodyMedium.copy(
            color = color
        )
    )
}

@Composable
fun TextForClickableLink(
    text: String,
    linkColor: Color,
    onClick: () -> Unit
) {
    TextButton(
        onClick = onClick
    ) {
        Text(
            text = text,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = linkColor
            )
        )
    }
}