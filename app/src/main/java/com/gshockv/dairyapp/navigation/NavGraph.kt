package com.gshockv.dairyapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.gshockv.dairyapp.ui.screen.auth.AuthScreen

@Composable
fun SetupNavGraph(startDestination: String, navController: NavHostController) {
  NavHost(
    startDestination = startDestination,
    navController = navController) {
    authRoute()
    homeRoute()
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

private fun NavGraphBuilder.homeRoute() {
  composable(route = AppScreen.Home.route) {
    // TODO: Implement me...
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
