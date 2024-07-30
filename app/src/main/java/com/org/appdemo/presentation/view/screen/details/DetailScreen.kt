package com.org.appdemo.presentation.view.screen.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.org.appdemo.R
import com.org.appdemo.presentation.theme.DemoAppTheme
import com.org.appdemo.presentation.view.screen.BaseAppComponentContainer


@Composable
fun DetailScreen(id: String, url: String, width: Int, height: Int, onBack: () -> Unit) {
    BaseAppComponentContainer(
        topBarTitle = stringResource(id = R.string.details),
        isBackEnable = true,
        onBack = onBack
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            ImageCard(url = url)
            Spacer(modifier = Modifier.height(16.dp))
            ContentDescription(
                title = stringResource(id = R.string.image_name),
                content = id
            )
            ContentDescription(
                title = stringResource(id = R.string.image_size),
                content = "${width}x${height}"
            )
        }
    }
}

@Composable
fun ImageCard(url: String) {
    Card(elevation = CardDefaults.cardElevation(8.dp)) {
        Image(
            painter = rememberAsyncImagePainter(model = url),
            contentDescription = null,
            modifier = Modifier
                .height(300.dp)
                .fillMaxWidth()
                .clip(RectangleShape),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun ContentDescription(title: String, content: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = title)
            Text(modifier = Modifier.padding(horizontal = 8.dp), text = ":")
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f), text = content, overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DemoAppTheme {
        Surface(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
        ) {
            DetailScreen(
                id = "00fccb6c-ea2d-4fbb-a63a-84c8eb33639f",
                url = "https://lexica-serve-encoded-images.sharif.workers.dev/md/00fccb6c-ea2d-4fbb-a63a-84c8eb33639f",
                width = 720,
                height = 1080
            ) {

            }
        }
    }
}