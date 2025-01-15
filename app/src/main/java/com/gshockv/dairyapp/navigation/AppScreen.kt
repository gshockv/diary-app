package com.gshockv.dairyapp.navigation

const val WRITE_SCREEN_ARGUMENT_KEY = "diaryId"

sealed class AppScreen(val route: String) {
  data object Authentication: AppScreen(route = "auth_screen")
  data object Home: AppScreen(route = "home_screen")

  data object Write: AppScreen(route = "write_screen?$WRITE_SCREEN_ARGUMENT_KEY=" +
      "{$WRITE_SCREEN_ARGUMENT_KEY}") {
    fun passDiaryId(diaryId: String) =
      "write_screen?$WRITE_SCREEN_ARGUMENT_KEY=$diaryId"
  }
}
