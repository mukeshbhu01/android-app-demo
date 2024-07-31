package com.org.appdemo.presentation.view.screen.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.org.appdemo.domain.model.ImageResponseModel
import com.org.appdemo.presentation.theme.DemoAppTheme
import com.org.appdemo.presentation.view.intent.HomeScreenIntent
import com.org.appdemo.presentation.view.screen.BaseAppComponentContainer
import com.org.appdemo.presentation.view.state.HomeScreenState
import com.org.appdemo.presentation.view.viewmodel.HomeViewModel


@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    navigateToDetail: (ImageResponseModel) -> Unit
) {
    val homeScreenState = homeViewModel.homeScreenState.collectAsStateWithLifecycle().value
    val searchQuery = homeViewModel.searchQuery.collectAsStateWithLifecycle().value

    HomeScreenRendering(state = homeScreenState, searchQuery) { homeIntent ->
        when (homeIntent) {
            is HomeScreenIntent.OnImageSelect -> navigateToDetail(homeIntent.selectedImage)
            else -> homeViewModel.handleHomeIntent(homeIntent)
        }
    }
}

@Composable
internal fun HomeScreenRendering(
    state: HomeScreenState,
    searchQuery: String = "",
    onHomeScreenIntent: (intent: HomeScreenIntent) -> Unit
) {
    BaseAppComponentContainer {
        SearchComponent(searchQuery = searchQuery, onHomeScreenIntent = onHomeScreenIntent)
        HomeContent(state = state, onHomeScreenIntent = onHomeScreenIntent)
    }
}

@Composable
fun HomeContent(state: HomeScreenState, onHomeScreenIntent: (intent: HomeScreenIntent) -> Unit) {
    when (state) {
        is HomeScreenState.InitialState -> NoImageView()
        is HomeScreenState.Loading -> LoadingComponent()
        is HomeScreenState.Success -> SuccessComponent(images = state.images, onHomeScreenIntent)
        is HomeScreenState.Error -> ErrorComponent(state.message, state.code)
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