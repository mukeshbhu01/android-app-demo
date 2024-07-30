package com.org.appdemo.presentation.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.org.appdemo.common.LogUtil
import com.org.appdemo.domain.mapper.toImageUiModel
import com.org.appdemo.domain.model.Result
import com.org.appdemo.domain.usecase.FetchImageUseCase
import com.org.appdemo.presentation.view.intent.HomeScreenIntent
import com.org.appdemo.presentation.view.state.HomeScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: FetchImageUseCase
) : ViewModel() {

    companion object {
        private const val TAG = "HomeViewModel"
    }

    private val _homeScreenState: MutableStateFlow<HomeScreenState> =
        MutableStateFlow(HomeScreenState.InitialState)
    val homeScreenState: StateFlow<HomeScreenState> = _homeScreenState.asStateFlow()


    fun handleHomeIntent(intent: HomeScreenIntent) {
        LogUtil.debugLog(log = "User intent -> $intent")
        when (intent) {
            is HomeScreenIntent.LoadImages -> {
                if (intent.query.isEmpty()) {
                    _homeScreenState.value = HomeScreenState.ShowEmptyQueryToast
                } else {
                    loadImages(intent.query)
                }
            }

            else -> {}
        }
    }

    private fun loadImages(query: String) {
        viewModelScope.launch {
            useCase.invoke(searchQuery = query)
                .onStart {
                    LogUtil.debugLog(log = "Load image..onStart")
                    _homeScreenState.value = HomeScreenState.Loading
                }
                .catch { throwable ->
                    LogUtil.debugLog(log = "Exception in loading image: ${throwable.printStackTrace()}")
                    _homeScreenState.value =
                        HomeScreenState.Error(throwable.message ?: "Error loading")
                }
                .collect { state ->
                    LogUtil.debugLog(TAG, "state -> $state")
                    when (state) {
                        is Result.Success -> {
                            _homeScreenState.value =
                                HomeScreenState.Success(images = state.responseData.toImageUiModel())
                        }

                        is Result.Error -> {
                            _homeScreenState.value = HomeScreenState.Error(message = state.message, code = state.code)
                        }
                    }
                }
        }
    }
}