package com.org.appdemo.presentation.ui.details.navigation

import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.org.appdemo.domain.model.ImageModel
import com.org.appdemo.presentation.ui.details.screen.DetailScreen
import com.org.appdemo.presentation.ui.home.model.DetailNavUiModel
import com.org.appdemo.presentation.ui.home.model.jsonToDetailsUiModel
import com.org.appdemo.presentation.ui.home.model.toDetailNavUiModel
import com.org.appdemo.presentation.ui.home.model.toJsonString

private const val DETAILS_ROUTE_BASE = "details_route"
private const val IMAGE_MODEL = "image_model"
private const val DETAILS_ROUTE = "$DETAILS_ROUTE_BASE/{$IMAGE_MODEL}"

fun NavController.navigateToDetails(image: ImageModel) {
    val route = "$DETAILS_ROUTE_BASE/${image.toDetailNavUiModel().toJsonString()}"
    navigate(route)
}

fun NavGraphBuilder.detailsScreen(onBack: () -> Unit) {
    composable(route = DETAILS_ROUTE,
        arguments = listOf(
            navArgument(name = IMAGE_MODEL) {
                type = NavType.StringType
            }
        )) { navBackStackEntry ->
        val detailsImage: DetailNavUiModel? =
            navBackStackEntry.arguments?.getString(IMAGE_MODEL)?.jsonToDetailsUiModel()
        Log.d("Demo", "Arguments= { image: $detailsImage")

        DetailScreen(detailNavUiModel = detailsImage, onBack = onBack)
    }
}