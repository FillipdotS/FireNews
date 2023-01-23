package com.fillipdots.firenews.util

import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.fillipdots.firenews.R

/**
 * An Image composable when in preview mode, otherwise it becomes a GlideImage.
 * This is useful because GlideImages don't work in preview mode.
 */
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun UtilGlideImage(
    source: Any?,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
    alpha: Float = 1.0f,
    colorFilter: ColorFilter? = null
) {
    if (LocalInspectionMode.current) {
        Image(
            modifier = modifier,
            painter = painterResource(R.drawable.test_cat),
            contentDescription = contentDescription,
            alignment = alignment,
            contentScale = contentScale,
            alpha = alpha,
            colorFilter = colorFilter
        )
    } else {
        GlideImage(
            modifier = modifier,
            model = source ?: "https://i.imgur.com/Z35qvPv.jpg",
            contentDescription = contentDescription,
            alignment = alignment,
            contentScale = contentScale,
            alpha = alpha,
            colorFilter = colorFilter
        )
    }
}
