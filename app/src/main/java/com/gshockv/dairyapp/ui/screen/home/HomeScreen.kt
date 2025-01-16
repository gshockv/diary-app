package com.gshockv.dairyapp.ui.screen.home

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gshockv.dairyapp.R
import com.gshockv.dairyapp.data.Diary
import com.gshockv.dairyapp.ui.theme.DiaryAppTheme

@Composable
fun HomeScreen(
  viewModel: HomeViewModel = hiltViewModel(),
  onDateFilterClick: () -> Unit,
  navigateToWrite: () -> Unit,
  modifier: Modifier = Modifier
) {

  val records = viewModel.uiState.collectAsState()

  Scaffold(
    modifier = modifier.fillMaxSize(),
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
  ) { innerPadding ->
    LazyColumn(
      modifier = Modifier.padding(innerPadding)
    ) {
      items(records.value) { record ->
        Log.d("HomeScreen", "${record.id}:${record.title}(${record.mood})")
        DiaryItem(diary = record)
      }
    }
  }
}

@Composable
private fun DiaryItem(
  diary: Diary,
  modifier: Modifier = Modifier
) {
  Card(
    modifier = modifier.padding(all = 8.dp)
  ) {
    Text(
      text = "${diary.id}:${diary.title}(${diary.mood})",
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 24.dp)
    )
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
