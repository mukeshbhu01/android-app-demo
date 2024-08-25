package com.org.appdemo.presentation.ui.home.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.org.appdemo.domain.model.ImageModel
import com.org.appdemo.domain.Result
import com.org.appdemo.domain.usecase.FetchImageUseCase
import com.org.appdemo.presentation.ui.home.effect.HomeScreenEffect
import com.org.appdemo.presentation.ui.home.intent.HomeScreenIntent
import com.org.appdemo.presentation.ui.home.state.HomeScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: FetchImageUseCase
) : ViewModel() {

    private val _homeScreenState: MutableStateFlow<HomeScreenState> =
        MutableStateFlow(HomeScreenState.InitialState)
    val homeScreenState: StateFlow<HomeScreenState> = _homeScreenState.asStateFlow()

    private val _effect:MutableSharedFlow<HomeScreenEffect> = MutableSharedFlow()
    val effect: SharedFlow<HomeScreenEffect> = _effect.asSharedFlow()

    fun sendActionEvent(intent: HomeScreenIntent) {
        when (intent) {
            is HomeScreenIntent.LoadImages -> {
                loadImages(intent.query)
            }

            is HomeScreenIntent.OnImageSelect -> {
                onImageSelected(intent.selectedImage)
            }
        }
    }

    private fun onImageSelected(image: ImageModel) {
        viewModelScope.launch {
            _effect.emit(HomeScreenEffect.NavigateToDetail(image))
        }
    }

    private fun loadImages(query: String) {
        _homeScreenState.value = HomeScreenState.Loading
        fetchImages(query = query)
    }

    private fun fetchImages(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = useCase(searchQuery = query)) {
                is Result.Success -> {
                    Log.d("Demo", "Result.Success")
                    _homeScreenState.value = HomeScreenState.Success(images = result.responseData)
                }

                is Result.Error -> {
                    Log.d("Demo", "Result.Error: ${result.throwable}")
                    _homeScreenState.value =
                        HomeScreenState.Error(message = result.message, code = result.code)
                }
            }
        }
    }
}