package com.org.appdemo.presentation.view.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.org.appdemo.domain.model.ImageResponseModel
import com.org.appdemo.presentation.view.intent.HomeScreenIntent

@Composable
fun SuccessComponent(
    images: List<ImageResponseModel>,
    onHomeScreenIntent: (intent: HomeScreenIntent) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        if (images.isEmpty()) {
            NoImageView()
        } else {
            ImageGridList(images = images, onHomeScreenIntent)
        }
    }
}

@Composable
fun ImageGridList(
    images: List<ImageResponseModel>,
    onHomeScreenIntent: (intent: HomeScreenIntent) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(4.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(items = images, key = {
            it.id
        }) { image ->
            ImageItemView(image = image) { selectedImage ->
                onHomeScreenIntent(HomeScreenIntent.OnImageSelect(selectedImage))
            }
        }
    }
}

@Composable
fun ImageItemView(image: ImageResponseModel, onImageCLick: (image: ImageResponseModel) -> Unit) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(12.dp))
            .background(color = MaterialTheme.colorScheme.onPrimaryContainer)
            .clickable { onImageCLick(image) }
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = image.url),
            contentDescription = "",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        )
    }
}