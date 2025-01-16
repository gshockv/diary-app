package com.gshockv.dairyapp.ui.screen.home

import android.content.res.Configuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.gshockv.dairyapp.R
import com.gshockv.dairyapp.ui.theme.DiaryAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(
  scrollBehavior: TopAppBarScrollBehavior,
  onDateFilterClick: () -> Unit
) {
  CenterAlignedTopAppBar(
    scrollBehavior = scrollBehavior,
    title = {
      Text(
        text = stringResource(R.string.home_title)
      )
    },
    actions = {
      IconButton(onClick = {}) {
        Icon(
          imageVector = Icons.Default.DateRange,
          contentDescription = "Date Filter action"
        )
      }
    }
  )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
private fun PreviewHomeTopBar_LightTheme() {
  DiaryAppTheme {
    HomeTopBar(
      scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(),
      onDateFilterClick = {}
    )
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun PreviewHomeTopBar_DarkTheme() {
  DiaryAppTheme {
    HomeTopBar(
      scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(),
      onDateFilterClick = {}
    )
  }
}
