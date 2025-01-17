package com.gshockv.dairyapp.ui.screen.write

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.gshockv.dairyapp.data.Diary
import com.gshockv.dairyapp.data.Mood
import com.gshockv.dairyapp.ui.component.DisplayAlertDialog
import com.gshockv.dairyapp.ui.component.galleryTestImages
import com.gshockv.dairyapp.ui.theme.DiaryAppTheme
import java.time.LocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WriteTopBar(
  selectedDiary: Diary?,
  onDeleteConfirmed: () -> Unit,
  onBackPressed: () -> Unit,
  modifier: Modifier = Modifier
) {
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
      Column() {
        Text(
          modifier = Modifier.fillMaxWidth(),
          text = "Mood",
          style = MaterialTheme.typography.titleLarge,
          textAlign = TextAlign.Center
        )
        Text(
          modifier = Modifier.fillMaxWidth(),
          text = "14 NOV 2025, 10:12",
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

      selectedDiary?.let {
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
