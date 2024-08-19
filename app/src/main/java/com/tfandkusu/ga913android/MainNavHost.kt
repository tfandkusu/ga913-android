package com.tfandkusu.ga913android

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tfandkusu.ga913android.analytics.AnalyticsEventSender
import com.tfandkusu.ga913android.ui.detail.LandmarkDetailScreen
import com.tfandkusu.ga913android.ui.detail.LandmarkDetailViewModel
import com.tfandkusu.ga913android.ui.detail.LandmarkDetailViewModelImpl
import com.tfandkusu.ga913android.ui.list.LandmarkListScreen
import com.tfandkusu.ga913android.ui.list.LandmarkListViewModel
import com.tfandkusu.ga913android.ui.list.LandmarkListViewModelImpl

@Composable
fun MainNavHost(analyticsEventSender: AnalyticsEventSender) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "list",
    ) {
        composable(route = "list") {
            val viewModel: LandmarkListViewModelImpl = hiltViewModel()
            LaunchedEffect(viewModel) {
                viewModel.event(LandmarkListViewModel.Event.Load)
                viewModel.effect.collect { effect ->
                    when (effect) {
                        is LandmarkListViewModel.Effect.NavigateToLandmarkDetail -> {
                            navController.navigate("detail/${effect.id}")
                        }
                    }
                }
            }
            LandmarkListScreen(viewModel)
        }
        composable(
            route = "detail/{id}",
            arguments = listOf(navArgument("id") { type = NavType.LongType }),
        ) { backStackEntry ->
            val landmarkId = requireNotNull(backStackEntry.arguments?.getLong("id"))
            val viewModel: LandmarkDetailViewModelImpl = hiltViewModel()
            LaunchedEffect(viewModel) {
                viewModel.event(LandmarkDetailViewModel.Event.Load(landmarkId))
            }
            LandmarkDetailScreen(
                viewModel = viewModel,
                analyticsEventSender = analyticsEventSender,
                onBackPressed = {
                    navController.popBackStack()
                },
            )
        }
    }
}
