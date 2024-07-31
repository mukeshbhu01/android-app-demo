package com.org.appdemo.presentation.ui.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.org.appdemo.domain.model.ImageModel
import com.org.appdemo.presentation.ui.home.screen.HomeScreen

const val HOME_ROUTE = "home_route"

fun NavGraphBuilder.homeScreen(navigateToDetail: (image: ImageModel) -> Unit) {
    composable(route = HOME_ROUTE) {
        HomeScreen(navigateToDetail = navigateToDetail)
    }
}