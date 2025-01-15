package com.gshockv.dairyapp.ui.screen.home

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.gshockv.dairyapp.R
import com.gshockv.dairyapp.ui.theme.DiaryAppTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
  onDateFilterClick: () -> Unit,
  navigateToWrite: () -> Unit,
  modifier: Modifier = Modifier
) {
  Scaffold(
    topBar = {
      HomeTopBar(
        onDateFilterClick = onDateFilterClick
      )
    },
    floatingActionButton = {
      ExtendedFloatingActionButton(
        onClick = navigateToWrite,
        icon = {
          Icon(
            imageVector = Icons.Default.Edit,
            contentDescription = null
          )
        },
        text = {
          Text(stringResource(R.string.navigate_to_writer))
        }
      )
    }
  ) {

  }
}

@Composable
@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
private fun PreviewHomeScreen_LightTheme() {
  DiaryAppTheme {
    HomeScreen(
      onDateFilterClick = {},
      navigateToWrite = {}
    )
  }
}

@Composable
@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun PreviewHomeScreen_DarkTheme() {
  DiaryAppTheme {
    HomeScreen(
      onDateFilterClick = {},
      navigateToWrite = {}
    )
  }
}
