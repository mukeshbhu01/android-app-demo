package com.org.appdemo.presentation.ui.home.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.org.appdemo.domain.model.ImageModel
import com.org.appdemo.presentation.ui.home.effect.HomeScreenEffect
import com.org.appdemo.presentation.ui.home.intent.HomeScreenIntent
import com.org.appdemo.presentation.ui.commonui.screen.BaseAppComponentContainer
import com.org.appdemo.presentation.ui.commonui.screen.ErrorViewComponent
import com.org.appdemo.presentation.ui.commonui.screen.LoadingViewComponent
import com.org.appdemo.presentation.ui.commonui.screen.NoImageView
import com.org.appdemo.presentation.ui.commonui.theme.DemoAppTheme
import com.org.appdemo.presentation.ui.home.state.HomeScreenState
import com.org.appdemo.presentation.ui.home.viewmodel.HomeViewModel
import kotlinx.coroutines.flow.collectLatest


@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    navigateToDetail: (image: ImageModel) -> Unit
) {
    val homeScreenState by homeViewModel.homeScreenState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        homeViewModel.effect.collectLatest { effect ->
            // Handle effects that require user interface (UI) changes
            when (effect) {
                is HomeScreenEffect.NavigateToDetail -> {
                    navigateToDetail(effect.selectedImage)
                }
            }
        }
    }

    BaseAppComponentContainer {
        ImageSearchView(onHomeScreenIntent = homeViewModel::sendActionEvent)
        HomeContentViews(state = homeScreenState, onHomeScreenIntent = homeViewModel::sendActionEvent)
    }
}


@Composable
fun HomeContentViews(state: HomeScreenState, onHomeScreenIntent: (intent: HomeScreenIntent) -> Unit) {
    when (state) {
        is HomeScreenState.InitialState -> NoImageView()
        is HomeScreenState.Loading -> LoadingViewComponent()
        is HomeScreenState.Success -> HomeContent(images = state.images, onHomeScreenIntent)
        is HomeScreenState.Error -> ErrorViewComponent(state.message, state.code)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DemoAppTheme {

    }
}