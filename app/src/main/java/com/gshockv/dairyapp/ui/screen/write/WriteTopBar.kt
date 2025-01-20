package com.gshockv.dairyapp.ui.screen.write

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gshockv.dairyapp.data.Diary
import com.gshockv.dairyapp.data.Mood
import com.gshockv.dairyapp.ui.component.DisplayAlertDialog
import com.gshockv.dairyapp.ui.theme.DiaryAppTheme
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WriteTopBar(
  selectedDiary: Diary,
  moodName: () -> String,
  onDeleteConfirmed: () -> Unit,
  onBackPressed: () -> Unit
) {

  val selectedDiaryDateTime = remember(selectedDiary) {
    selectedDiary.date.format(DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm")).uppercase()
  }

  val titleExtraPadding = remember(selectedDiary.id > 0) {
    if (selectedDiary.id > 0) 46.dp else 0.dp
  }

  CenterAlignedTopAppBar(
    navigationIcon = {
      IconButton(onClick = onBackPressed) {
        Icon(
          imageVector = Icons.AutoMirrored.Filled.ArrowBack,
          contentDescription = "Back Icon"
        )
      }
    },
    title = {
      Column(
        modifier = Modifier.fillMaxWidth().padding(end = titleExtraPadding),
        horizontalAlignment = Alignment.End
      ) {
        Text(
          modifier = Modifier.fillMaxWidth(),
          text = moodName(),
          style = MaterialTheme.typography.titleLarge,
          textAlign = TextAlign.Center
        )
        Text(
          modifier = Modifier.fillMaxWidth(),
          text = selectedDiaryDateTime,
          style = MaterialTheme.typography.bodySmall,
          textAlign = TextAlign.Center
        )
      }
    },
    actions = {
      DeleteDiaryAction(
        selectedDiary = selectedDiary,
        onDeleteConfirmed = onDeleteConfirmed
      )
    }
  )
}

@Composable
private fun DeleteDiaryAction(
  selectedDiary: Diary,
  onDeleteConfirmed: () -> Unit
) {
  var openDialog by remember { mutableStateOf(false) }

  DisplayAlertDialog(
    title = "Delete",
    message = "Delete this diary note '${selectedDiary.title}'?",
    dialogOpened = openDialog,
    onDialogClosed = { openDialog = false },
    onYesClicked = onDeleteConfirmed
  )

  IconButton(
    onClick = { openDialog = true },
    enabled = selectedDiary.id > 0
  ) {
    Icon(
      imageVector = Icons.Default.Delete,
      contentDescription = null
    )
  }
}

@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
private fun PreviewWriteTopBap_LightTheme() {
  DiaryAppTheme {
    Surface {
      WriteTopBar(
        moodName = { "Mood Name" },
        selectedDiary = Diary(
          id = 42,
          title = "Test Diary",
          description = "Lorem ipsum...",
          mood = Mood.Calm,
          images = listOf(),
          date = LocalDateTime.now()
        ),
        onDeleteConfirmed = {},
        onBackPressed = {}
      )
    }
  }
}