package com.org.appdemo.presentation.view.screen.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.org.appdemo.R
import com.org.appdemo.common.Util
import com.org.appdemo.presentation.view.intent.HomeScreenIntent

@Composable
fun SearchComponent(searchQuery: String, onHomeScreenIntent: (intent: HomeScreenIntent) -> Unit) {
    val keyboardManager = LocalSoftwareKeyboardController.current
    val context = LocalContext.current

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        placeholder = {
            Text(text = stringResource(id = R.string.search_text))
        },
        value = searchQuery, onValueChange = {
            onHomeScreenIntent(HomeScreenIntent.SearchQuery(searchQuery = it))
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardManager?.hide()
                if(searchQuery.isEmpty())
                    Util.showToastMessage(context = context, message = context.getString(R.string.empty_query_message))
                else
                    onHomeScreenIntent(HomeScreenIntent.LoadImages(query = searchQuery))
            }
        ),
        singleLine = true,
        trailingIcon = {
            IconButton(onClick = {
                if(searchQuery.isEmpty())
                    Util.showToastMessage(context = context, message = context.getString(R.string.empty_query_message))
                else
                    onHomeScreenIntent(HomeScreenIntent.LoadImages(query = searchQuery))
            }) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Clear",
                    tint = Color.Gray
                )
            }
        }
    )
}