package com.example.spacexchallenge.ui.utils

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import coil.compose.AsyncImage
import com.example.spacexchallenge.ui.theme.Sizes

@Composable
fun ImageFromUrlWithDefaultRocket(
    modifier: Modifier,
    urlImage: String,
    contentDescription: String? = null
) {
    if (urlImage.isNotBlank()) {
        AsyncImage(
            modifier = modifier,
            model = urlImage,
            contentDescription = contentDescription,
            contentScale = ContentScale.Crop
        )
    } else {
        Text(
            modifier = modifier,
            text = String(Character.toChars(0x1F680)),
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = Sizes.emojiItemSize
            )
        )
    }
}