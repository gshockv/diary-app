package com.gshockv.dairyapp.ui.screen.home

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gshockv.dairyapp.model.Diary
import com.gshockv.dairyapp.ui.component.DateListHeader
import com.gshockv.dairyapp.ui.component.DiaryHolder
import com.gshockv.dairyapp.ui.theme.DiaryAppTheme
import java.time.LocalDate

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeContent(
  diaryNotes: Map<LocalDate, List<Diary>>,
  openDiary: (Int) -> Unit,
  modifier: Modifier = Modifier
) {
  if (diaryNotes.isNotEmpty()) {
    LazyColumn(
      modifier = modifier.padding(horizontal = 24.dp)
    ) {
      diaryNotes.forEach { (localDate, diaries) ->
        stickyHeader(key = localDate) {
          DateListHeader(localDate = localDate)
        }

        items(
          items = diaries,
          key = { it.id }
        ) {

          Log.d("HOME", "Diary = $it")

          DiaryHolder(
            diary = it,
            onClick = openDiary
          )
        }
      }
    }
  } else {
    EmptyPage()
  }
}

@Composable
@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
private fun PreviewHomeContent_LightTheme() {
  DiaryAppTheme {
    Surface {
      HomeContent(
        diaryNotes = emptyMap(),
        openDiary = { }
      )
    }
  }
}
