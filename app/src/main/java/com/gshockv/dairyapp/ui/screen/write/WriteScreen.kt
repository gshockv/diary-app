package com.gshockv.dairyapp.ui.screen.write

import android.content.res.Configuration
import android.net.Uri
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gshockv.dairyapp.model.GalleryImage
import com.gshockv.dairyapp.model.Mood
import com.gshockv.dairyapp.model.rememberGalleryState
import com.gshockv.dairyapp.model.testImages
import com.gshockv.dairyapp.ui.theme.DiaryAppTheme

@Composable
fun WriteScreen(
  onBackPressed: () -> Unit,
  modifier: Modifier = Modifier,
  selectedDiaryId: Int = 0,
  viewModel: WriteViewModel = hiltViewModel()
) {
  val uiState = viewModel.uiState.collectAsStateWithLifecycle()
  val galleryState = rememberGalleryState()

  LaunchedEffect(key1 = selectedDiaryId) {
    viewModel.loadDiaryDetails(selectedDiaryId)
  }

  val moodPagerState = rememberPagerState(pageCount = {
    Mood.entries.size
  })
  val moodPageIndex by remember {
    derivedStateOf { moodPagerState.currentPage }
  }

  LaunchedEffect(moodPageIndex) {
    viewModel.setMood(Mood.entries[moodPageIndex])
  }

  LaunchedEffect(uiState.value.diary.mood) {
    moodPagerState.scrollToPage(uiState.value.diary.mood.ordinal)
  }

  LaunchedEffect(uiState.value.diary) {
    uiState.value.diary.testImages.forEach {
      galleryState.addImage(
        GalleryImage(
        drawableId = it,
        image = Uri.EMPTY
      )
      )
    }
  }

  Scaffold(
    topBar = {
      WriteTopBar(
        selectedDiary = uiState.value.diary,
        moodName = { Mood.entries[moodPageIndex].name },
        onDeleteConfirmed = {
          viewModel.deleteDiary()
          onBackPressed()
        },
        onBackPressed = onBackPressed
      )
    }
  ) { innerPadding ->
    WriteContent(
      title = uiState.value.diary.title,
      onTitleChanged = { viewModel.setTitle(it) },
      description = uiState.value.diary.description,
      onDescriptionChanged = { viewModel.setDescription(it) },
      onSaveClick = {
        viewModel.setGalleryImages(galleryState.images)
        viewModel.saveDiary()
        onBackPressed()
      },
      moodPagerState = moodPagerState,
      galleryState = galleryState,
      modifier = Modifier.padding(innerPadding),
      onImageSelect = { uri ->
        galleryState.addImage(GalleryImage(
          drawableId = 0,
          image = uri
        ))
      }
    )
  }
}

@Composable
@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
private fun PreviewWriteScreen_LightTheme() {
  DiaryAppTheme {
    WriteScreen(
      onBackPressed = {
      }
    )
  }
}

@Composable
@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun PreviewWriteScreen_DarkTheme() {
  DiaryAppTheme {
    WriteScreen(
      onBackPressed = {
      },
    )
  }
}
