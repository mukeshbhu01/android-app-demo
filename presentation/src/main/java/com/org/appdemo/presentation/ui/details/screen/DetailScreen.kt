package com.org.appdemo.presentation.ui.details.screen

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.org.appdemo.presentation.R
import com.org.appdemo.presentation.ui.commonui.screen.BaseAppComponentContainer
import com.org.appdemo.presentation.ui.commonui.screen.ErrorView
import com.org.appdemo.presentation.ui.commonui.theme.DemoAppTheme
import com.org.appdemo.presentation.ui.commonui.wrapper.AsyncImageLoaderWrapper
import com.org.appdemo.presentation.ui.home.model.DetailNavUiModel

@Composable
fun DetailScreen(
    detailNavUiModel: DetailNavUiModel?,
    onBack: () -> Unit
) {

    BaseAppComponentContainer(
        topBarTitle = stringResource(id = R.string.details),
        isBackEnable = true,
        onBack = onBack
    ) {
        if (detailNavUiModel != null)
            DetailsContent(detailNavUiModel)
        else
            ErrorView(
                message = stringResource(id = R.string.something_went_wrong),
                imageRes = R.drawable.no_photograph
            )
    }
}

@Composable
fun DetailsContent(imageModel: DetailNavUiModel) {
    Column(modifier = Modifier.padding(16.dp)) {

        ImageCard(url = imageModel.url)
        Spacer(modifier = Modifier.height(16.dp))

        ContentDescription(
            title = stringResource(id = R.string.image_model),
            content = imageModel.model
        )
        ContentDescription(
            title = stringResource(id = R.string.image_description),
            content = imageModel.prompt
        )
        ContentDescription(
            title = stringResource(id = R.string.image_name),
            content = imageModel.id
        )
        ContentDescription(
            title = stringResource(id = R.string.image_size),
            content = "${imageModel.width}x${imageModel.height}"
        )
    }
}

@Composable
private fun ImageCard(url: String) {
    Card(elevation = CardDefaults.cardElevation(8.dp)) {
        AsyncImageLoaderWrapper(
            modifier = Modifier
                .height(300.dp)
                .fillMaxWidth()
                .clip(RectangleShape), url = url
        )
    }
}

@Composable
private fun ContentDescription(title: String, content: String) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
        Text(
            text = title, modifier = Modifier
                .fillMaxWidth()
                .weight(0.2f),
            fontSize = 13.sp
        )
        Text(modifier = Modifier.padding(horizontal = 8.dp), text = ":")
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.7f), text = content, overflow = TextOverflow.Ellipsis, fontSize = 13.sp
        )
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
            DetailsContent(
                DetailNavUiModel(
                    id = "00fccb6c-ea2d-4fbb-a63a-84c8eb33639f",
                    url = "https://lexica-serve-encoded-images.sharif.workers.dev/md/00fccb6c-ea2d-4fbb-a63a-84c8eb33639f",
                    prompt = "cat - superman, hyperrealism, no blur, 4 k resolution, ultra detailed, style of zdzisław beksinski ",
                    model = "stable-diffusion",
                    promptId = "f836bdb0-9574-454a-bd28-2504bfc99e29",
                    width = 720,
                    height = 1080
                )
            )
        }
    }
}