package com.org.appdemo.presentation.view.screen.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.org.appdemo.presentation.theme.DemoAppTheme
import com.org.appdemo.presentation.view.intent.HomeScreenIntent
import com.org.appdemo.presentation.view.model.ImageUiModel
import com.org.appdemo.presentation.view.screen.BaseAppComponentContainer
import com.org.appdemo.presentation.view.screen.ShowEmptyQueryToastMessage
import com.org.appdemo.presentation.view.state.HomeScreenState
import com.org.appdemo.presentation.view.viewmodel.HomeViewModel


@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    navigateToDetail: (ImageUiModel) -> Unit
) {
    val homeScreenState = homeViewModel.homeScreenState.collectAsStateWithLifecycle().value

    HomeScreenRendering(state = homeScreenState) { homeIntent ->
        when (homeIntent) {
            is HomeScreenIntent.OnImageSelect -> navigateToDetail(homeIntent.selectedImage)
            else -> homeViewModel.handleHomeIntent(homeIntent)
        }
    }
}

@Composable
fun HomeScreenRendering(
    state: HomeScreenState,
    onHomeScreenIntent: (intent: HomeScreenIntent) -> Unit
) {
    BaseAppComponentContainer {
        SearchComponent { searchQuery ->
            onHomeScreenIntent(HomeScreenIntent.LoadImages(query = searchQuery))
        }
        HomeContent(state = state, onHomeScreenIntent)
    }
}

@Composable
fun HomeContent(state: HomeScreenState, onHomeScreenIntent: (intent: HomeScreenIntent) -> Unit) {
    when (state) {
        is HomeScreenState.InitialState -> NoImageView()
        is HomeScreenState.Loading -> LoadingComponent()
        is HomeScreenState.Success -> SuccessComponent(images = state.images, onHomeScreenIntent)
        is HomeScreenState.Error -> ErrorComponent(state.message, state.code)
        is HomeScreenState.ShowEmptyQueryToast -> ShowEmptyQueryToastMessage()
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DemoAppTheme {
        HomeScreenRendering(state = HomeScreenState.InitialState) {

        }
    }
}