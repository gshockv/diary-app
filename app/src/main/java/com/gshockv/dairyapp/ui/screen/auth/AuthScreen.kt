package com.gshockv.dairyapp.ui.screen.auth

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.gshockv.dairyapp.ui.theme.DiaryAppTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AuthScreen(
  loadingState: Boolean,
  onDataLoaded: () -> Unit,
  onButtonClicked: () -> Unit,
  modifier: Modifier = Modifier
) {
  SideEffect {
    onDataLoaded()
  }

  Scaffold(
    modifier = modifier
      .background(MaterialTheme.colorScheme.surface)
      .systemBarsPadding()
  ) {
    AuthContent(
      loadingState = loadingState,
      onButtonClicked = onButtonClicked
    )
  }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
private fun PreviewAuthScreen_LightTheme() {
  DiaryAppTheme {
    Scaffold {
      AuthContent(
        loadingState = false,
        onButtonClicked = {}
      )
    }
  }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun PreviewAuthScreen_DarkTheme() {
  DiaryAppTheme {
    Scaffold {
      AuthContent(
        loadingState = false,
        onButtonClicked = {}
      )
    }
  }
}
