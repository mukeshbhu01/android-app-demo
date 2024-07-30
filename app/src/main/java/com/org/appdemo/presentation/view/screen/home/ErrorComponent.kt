package com.org.appdemo.presentation.view.screen.home

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.org.appdemo.R
import com.org.appdemo.data.service.ApiConstants

@Composable
fun ErrorComponent(message: String, code: String) {
    when (code) {
        ApiConstants.ErrorCode.NO_INTERNET -> ErrorView(
            message = stringResource(id = R.string.no_image_available),
            imageVector = Icons.Outlined.Info
        )

        else -> ErrorView(message = message, imageVector = Icons.Outlined.Warning)
    }
}

@Composable
fun NoImageView() {
    ErrorView(
        message = stringResource(id = R.string.no_image_available),
        imageRes = R.drawable.no_photographs
    )
}

@Composable
fun ErrorView(message: String, @DrawableRes imageRes: Int) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = "Error",
            modifier = Modifier.size(56.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = message, textAlign = TextAlign.Center)
    }
}

@Composable
fun ErrorView(message: String, imageVector: ImageVector) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            imageVector = imageVector,
            contentDescription = "Error",
            modifier = Modifier.size(56.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = message, textAlign = TextAlign.Center)
    }
}
