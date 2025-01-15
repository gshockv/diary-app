package com.gshockv.dairyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.gshockv.dairyapp.navigation.AppScreen
import com.gshockv.dairyapp.navigation.SetupNavGraph
import com.gshockv.dairyapp.ui.theme.DiaryAppTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    installSplashScreen()
    enableEdgeToEdge()
    setContent {
      DiaryAppTheme {
        val navController = rememberNavController()
        SetupNavGraph(
          navController = navController,
          startDestination = AppScreen.Authentication.route
        )
      }
    }
  }
}
