package com.org.appdemo.presentation.ui.home.screen

import android.widget.Toast
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.org.appdemo.presentation.ui.home.intent.HomeScreenIntent
import com.org.appdemo.presentation.R

@Composable
fun ImageSearchView(onHomeScreenIntent: (intent: HomeScreenIntent) -> Unit) {
    val keyboardManager = LocalSoftwareKeyboardController.current
    val context = LocalContext.current

    var searchQuery by rememberSaveable {
        mutableStateOf("")
    }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        placeholder = {
            Text(text = stringResource(id = R.string.search_text))
        },
        value = searchQuery, onValueChange = {
            searchQuery = it
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardManager?.hide()
                if(searchQuery.isEmpty())
                    Toast.makeText(context, context.getString(R.string.empty_query_message), Toast.LENGTH_SHORT).show()
                else
                    onHomeScreenIntent(HomeScreenIntent.LoadImages(query = searchQuery))
            }
        ),
        singleLine = true,
        trailingIcon = {
            IconButton(onClick = {
                if(searchQuery.isEmpty())
                    Toast.makeText(context, context.getString(R.string.empty_query_message), Toast.LENGTH_SHORT).show()
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