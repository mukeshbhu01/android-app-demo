package com.org.appdemo.presentation.ui.commonui.wrapper

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.org.appdemo.presentation.R


@Composable
fun AsyncImageLoaderWrapper(modifier: Modifier, url: String) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current).data(url).apply {
            placeholder(drawableResId = R.drawable.ic_launcher_foreground)
        }.build()
    )
    Image(
        painter = painter,
        contentDescription = null,
        modifier = modifier,
        contentScale = ContentScale.Crop
    )
}