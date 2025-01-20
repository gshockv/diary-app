package com.gshockv.dairyapp.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.gshockv.dairyapp.ui.screen.auth.AuthScreen
import com.gshockv.dairyapp.ui.screen.home.HomeScreen
import com.gshockv.dairyapp.ui.screen.write.WriteScreen
import kotlinx.serialization.Serializable

@Serializable
object AuthRoute

@Serializable
object HomeRoute

@Serializable
data class WriteRoute(val id: Int = 0)

@Composable
fun Main(
  navController: NavHostController,
  onDataLoaded: () -> Unit
) {
  SetupNavGraph(
    navController = navController,
    onDataLoaded = onDataLoaded
  )
}

@Composable
private fun SetupNavGraph(
  navController: NavHostController,
  onDataLoaded: () -> Unit
) {
  NavHost(
    startDestination = HomeRoute,
    navController = navController
  ) {
    authRoute()
    homeRoute(
      openNewDiaryEditor = {
        navController.navigate(WriteRoute(id = 0))
      },
      openDiaryForEdit = {
        navController.navigate(WriteRoute(id = it))
      },
      onDataLoaded = onDataLoaded
    )
    writeRoute(
      onBackPressed = {
        navController.popBackStack()
      }
    )
  }
}

private fun NavGraphBuilder.authRoute() {
  composable<AuthRoute> {
    AuthScreen(
      loadingState = false,
      onButtonClicked = { }
    )
  }
}

private fun NavGraphBuilder.homeRoute(
  openNewDiaryEditor: () -> Unit,
  openDiaryForEdit: (Int) -> Unit,
  onDataLoaded: () -> Unit
) {
  composable<HomeRoute> {
    HomeScreen(
      openNewDiaryEditor = openNewDiaryEditor,
      openDiaryForEdit = openDiaryForEdit,
      onDataLoaded = onDataLoaded
    )
  }
}

private fun NavGraphBuilder.writeRoute(onBackPressed: () -> Unit) {
  composable<WriteRoute> { backStackEntry ->
    val route: WriteRoute = backStackEntry.toRoute()

    WriteScreen(
      selectedDiaryId = route.id,
      onDeleteConfirmed = {},
      onBackPressed = onBackPressed
    )
  }
}
