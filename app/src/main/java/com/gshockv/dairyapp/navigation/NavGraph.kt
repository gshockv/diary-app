package com.gshockv.dairyapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.gshockv.dairyapp.ui.screen.auth.AuthScreen
import com.gshockv.dairyapp.ui.screen.home.HomeScreen
import com.gshockv.dairyapp.ui.screen.home.HomeViewModel
import com.gshockv.dairyapp.util.RequestState

@Composable
fun SetupNavGraph(
  startDestination: String, navController: NavHostController,
  onDataLoaded: () -> Unit
) {
  NavHost(
    startDestination = startDestination,
    navController = navController
  ) {
    authRoute()
    homeRoute(
      navigateToWrite = {
        navController.navigate(AppScreen.Write.route)
      },
      onDataLoaded = onDataLoaded
    )
    writeRoute()
  }
}

private fun NavGraphBuilder.authRoute() {
  composable(route = AppScreen.Authentication.route) {
    AuthScreen(
      loadingState = false,
      onButtonClicked = {
      }
    )
  }
}

private fun NavGraphBuilder.homeRoute(
  navigateToWrite: () -> Unit,
  onDataLoaded: () -> Unit
) {
  composable(route = AppScreen.Home.route) {
    val viewModel: HomeViewModel = hiltViewModel()
    val diariesState = viewModel.diariesState

    LaunchedEffect(key1 = diariesState) {
      if (diariesState.value !is RequestState.Loading) {
        onDataLoaded()
      }
    }

    HomeScreen(
      diariesState = diariesState,
      onDateFilterClick = {
        TODO("Implement me")
      },
      navigateToWrite = navigateToWrite
    )
  }
}

private fun NavGraphBuilder.writeRoute() {
  composable(
    route = AppScreen.Write.route,
    arguments = listOf(navArgument(WRITE_SCREEN_ARGUMENT_KEY) {
      type = NavType.StringType
      nullable = true
      defaultValue = null
    })
  ) {
    // TODO: Implement me...
  }
}
