package com.gshockv.dairyapp.ui.screen.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.gshockv.dairyapp.R
import com.gshockv.dairyapp.model.Diary
import com.gshockv.dairyapp.ui.theme.DiaryAppTheme
import com.gshockv.dairyapp.model.RequestState
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
  openNewDiaryEditor: () -> Unit,
  openDiaryForEdit: (Int) -> Unit,
  onDataLoaded: ()  -> Unit,
  viewModel: HomeViewModel = hiltViewModel(),
  modifier: Modifier = Modifier
) {
  val state = viewModel.diariesState.collectAsState()

  LaunchedEffect(key1 = state) {
    if (state.value !is RequestState.Loading) {
      onDataLoaded()
    }
  }

  val appBarScrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

  Scaffold(
    modifier = modifier.nestedScroll(appBarScrollBehavior.nestedScrollConnection),
    topBar = {
      HomeTopBar(
        scrollBehavior = appBarScrollBehavior,
        onDateFilterClick = { }
      )
    },
    floatingActionButton = {
      ExtendedFloatingActionButton(
        onClick = openNewDiaryEditor,
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
          openDiary = openDiaryForEdit,
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
    }
  }
}

@Composable
@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
private fun PreviewHomeScreen_LightTheme() {
  DiaryAppTheme {
    HomeScreen(
      onDataLoaded = {},
      openNewDiaryEditor = {},
      openDiaryForEdit = {}
    )
  }
}

@Composable
@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun PreviewHomeScreen_DarkTheme() {
  DiaryAppTheme {
    HomeScreen(
      onDataLoaded = {},
      openNewDiaryEditor = {},
      openDiaryForEdit = {}
    )
  }
}
