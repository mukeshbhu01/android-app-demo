package com.org.appdemo.presentation.view.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.org.appdemo.common.LogUtil
import com.org.appdemo.common.Util
import com.org.appdemo.presentation.view.screen.details.DetailScreen
import com.org.appdemo.presentation.view.screen.home.HomeScreen


@Composable
fun NavigationGraph(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen{ imageUiModel ->
                val encodedUrl = Util.encodeUrl(imageUiModel.url)
                navHostController.navigate("${Screen.Details.route}/${imageUiModel.id}/${encodedUrl}/${imageUiModel.width}/${imageUiModel.height}")
            }
        }

        composable(route = "${Screen.Details.route}/{${Arguments.ID}}/{${Arguments.URL}}/{${Arguments.WIDTH}}/{${Arguments.HEIGHT}}",
            arguments = listOf(
                navArgument(name = Arguments.ID) {
                    type = NavType.StringType
                },
                navArgument(name = Arguments.URL) {
                    type = NavType.StringType
                },
                navArgument(name = Arguments.WIDTH) {
                    type = NavType.IntType
                },
                navArgument(name = Arguments.HEIGHT) {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val id: String = backStackEntry.arguments?.getString(Arguments.ID) ?: ""
            val url: String = backStackEntry.arguments?.getString(Arguments.URL) ?: ""
            val width: Int = backStackEntry.arguments?.getInt(Arguments.WIDTH) ?: 0
            val height: Int = backStackEntry.arguments?.getInt(Arguments.HEIGHT) ?: 0

            LogUtil.debugLog(log = "Arguments= {\nid:$id,\nurl:$url,\nwidth:$width,\nheight:$height\n}")

            DetailScreen(id = id, url = url, width = width, height = height) {
                //Handle back
                navHostController.popBackStack()
            }
        }
    }
}