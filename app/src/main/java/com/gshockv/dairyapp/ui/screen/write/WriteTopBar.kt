package com.gshockv.dairyapp.ui.screen.write

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WriteTopBar(
  selectedDiary: Diary,
  moodName: () -> String,
  onDeleteConfirmed: () -> Unit,
  onBackPressed: () -> Unit
) {

  val currentDate by remember { mutableStateOf(LocalDate.now()) }
  val currentTime by remember { mutableStateOf(LocalTime.now()) }
  val formattedDate = remember(currentDate) {
    currentDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy")).uppercase()
  }
  val formattedTime = remember(currentTime) {
    currentTime.format(DateTimeFormatter.ofPattern("HH:mm")).uppercase()
  }

  val selectedDiaryDateTime = remember(selectedDiary) {
    selectedDiary.date.format(DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm")).uppercase()
  }

  val titleExtraPadding = remember(selectedDiary.id > 0) {
    if (selectedDiary.id > 0) 36.dp else 0.dp
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
        modifier = Modifier.fillMaxWidth().padding(start = titleExtraPadding),
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
          text = if (selectedDiary.id > 0) selectedDiaryDateTime else "$formattedDate, $formattedTime",
          style = MaterialTheme.typography.bodySmall,
          textAlign = TextAlign.Center
        )
      }
    },
    actions = {
      IconButton(onClick = {}) {
        Icon(
          imageVector = Icons.Default.DateRange,
          contentDescription = "Date Icon",
          tint = MaterialTheme.colorScheme.onSurface
        )
      }

      if (selectedDiary.id > 0) {
        DeleteDiaryAction(
          selectedDiary = selectedDiary,
          onDeleteConfirmed = onDeleteConfirmed
        )
      }
    }
  )
}

@Composable
private fun DeleteDiaryAction(
  selectedDiary: Diary?,
  onDeleteConfirmed: () -> Unit
) {
  var expanded by remember { mutableStateOf(false) }
  var openDialog by remember { mutableStateOf(false) }

  DropdownMenu(
    expanded = expanded,
    onDismissRequest = { expanded = false }
  ) {
    DropdownMenuItem(
      text = {
        Text(text = "Delete")
      },
      onClick = {
        openDialog = true
        expanded = false
      }
    )
  }
  DisplayAlertDialog(
    title = "Delete",
    message = "Delete this diary note '${selectedDiary?.title}'?",
    dialogOpened = openDialog,
    onDialogClosed = { openDialog = false },
    onYesClicked = onDeleteConfirmed
  )
  IconButton(onClick = { expanded = !expanded }) {
    Icon(
      imageVector = Icons.Default.MoreVert,
      contentDescription = null,
      tint = MaterialTheme.colorScheme.onSurface
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