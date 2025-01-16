package com.gshockv.dairyapp.ui.screen.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gshockv.dairyapp.R
import com.gshockv.dairyapp.data.Diaries
import com.gshockv.dairyapp.data.Diary
import com.gshockv.dairyapp.ui.theme.DiaryAppTheme
import com.gshockv.dairyapp.util.RequestState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
  diariesState: StateFlow<Diaries>,
  onDateFilterClick: () -> Unit,
  navigateToWrite: () -> Unit,
  modifier: Modifier = Modifier
) {
  val state = diariesState.collectAsState()

  val appBarScrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
  //val fabScrollBehavior = FloatingActionButtonDefaults.

  Scaffold(
    modifier = modifier.nestedScroll(appBarScrollBehavior.nestedScrollConnection),
    topBar = {
      HomeTopBar(
        scrollBehavior = appBarScrollBehavior,
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

    when (state.value) {
      is RequestState.Success -> {
        HomeContent(
          diaryNotes = (state.value as RequestState.Success<Map<LocalDate, List<Diary>>>).data,
          onClick = {},
          modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
        )
      }
      is RequestState.Loading -> {
        Box(
          modifier = Modifier.fillMaxSize(),
          contentAlignment = Alignment.Center
        ) {
          CircularProgressIndicator()
        }
      }
      is RequestState.Idle -> {
      }
      else -> {}
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
      diariesState = MutableStateFlow(RequestState.Idle),
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
      MutableStateFlow(RequestState.Idle),
      onDateFilterClick = {},
      navigateToWrite = {}
    )
  }
}
