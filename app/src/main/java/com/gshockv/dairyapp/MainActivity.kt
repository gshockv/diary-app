package com.gshockv.dairyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.gshockv.dairyapp.navigation.Main
import com.gshockv.dairyapp.ui.theme.DiaryAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

  private var keepSplashOpened = true

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    installSplashScreen().setKeepOnScreenCondition {
      keepSplashOpened
    }

    enableEdgeToEdge()

    setContent {
      DiaryAppTheme {
        val navController = rememberNavController()
        Main(
          navController = navController,
          onDataLoaded = {
            keepSplashOpened = false
          }
        )
      }
    }
  }
}
