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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.org.appdemo.R

@Composable
fun SearchComponent(onSearchClick: (query: String) -> Unit) {
    val keyboardManager = LocalSoftwareKeyboardController.current

    var query by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        placeholder = {
            Text(text = stringResource(id = R.string.search_text))
        },
        value = query, onValueChange = {
            if (it.isNotEmpty()) {
                errorMessage = ""
            }
            query = it
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardManager?.hide()
                if (query.isNotEmpty()) {
                    onSearchClick(query)
                } else {
                    errorMessage = "Enter search query first!"
                }
            }
        ),
        singleLine = true,
        isError = errorMessage.isNotEmpty(),
        trailingIcon = {
            IconButton(onClick = {
                if (query.isNotEmpty()) {
                    onSearchClick(query)
                } else {
                    errorMessage = "Enter search query first!"
                }
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