package com.gshockv.dairyapp.ui.screen.write

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gshockv.dairyapp.data.Diary
import com.gshockv.dairyapp.data.Mood
import com.gshockv.dairyapp.ui.theme.DiaryAppTheme
import java.time.LocalDateTime

@Composable
fun WriteScreen(
  selectedDiary: Diary?,
  moodPagerState: PagerState,
  onDeleteConfirmed: () -> Unit,
  onBackPressed: () -> Unit,
  modifier: Modifier = Modifier
) {
  Scaffold(
    topBar = {
      WriteTopBar(
        selectedDiary = selectedDiary,
        onDeleteConfirmed = onDeleteConfirmed,
        onBackPressed = onBackPressed
      )
    }
  ) { innerPadding ->
    WriteContent(
      title = "",
      onTitleChanged = {},
      description = "",
      onDescriptionChanged = {},
      moodPagerState = moodPagerState,
      modifier = Modifier.padding(innerPadding)
    )
  }
}

@Composable
@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
private fun PreviewWriteScreen_LightTheme() {
  DiaryAppTheme {
    WriteScreen(
      selectedDiary = Diary(
        id = 42,
        title = "Test Diary",
        description = "Lorem ipsum...",
        mood = Mood.Calm,
        images = listOf(),
        date = LocalDateTime.now()
      ),
      onDeleteConfirmed = {},
      onBackPressed = {
      },
      moodPagerState = rememberPagerState(pageCount = { Mood.entries.size })
    )
  }
}

@Composable
@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun PreviewWriteScreen_DarkTheme() {
  DiaryAppTheme {
    WriteScreen(
      selectedDiary = Diary(
        id = 42,
        title = "Test Diary",
        description = "Lorem ipsum...",
        mood = Mood.Calm,
        images = listOf(),
        date = LocalDateTime.now()
      ),
      onDeleteConfirmed = {},
      onBackPressed = {
      },
      moodPagerState = rememberPagerState(pageCount = { Mood.entries.size })
    )
  }
}
