package com.org.appdemo.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.org.appdemo.presentation.ui.details.navigation.detailsScreen
import com.org.appdemo.presentation.ui.details.navigation.navigateToDetails
import com.org.appdemo.presentation.ui.home.navigation.HOME_ROUTE
import com.org.appdemo.presentation.ui.home.navigation.homeScreen


@Composable
fun AppNavigationGraph(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = HOME_ROUTE) {
        homeScreen(navHostController::navigateToDetails)
        detailsScreen(navHostController::navigateUp)
    }
}

