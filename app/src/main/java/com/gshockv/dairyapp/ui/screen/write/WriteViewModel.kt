package com.gshockv.dairyapp.ui.screen.write

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gshockv.dairyapp.model.Diary
import com.gshockv.dairyapp.data.DiaryRepository
import com.gshockv.dairyapp.model.GalleryImage
import com.gshockv.dairyapp.model.Mood
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

data class UiState(
  val selectedDiaryId: Int = 0,
  val diary: Diary = prepareEmptyDiary()
)

@HiltViewModel
class WriteViewModel @Inject constructor(
  private val repository: DiaryRepository
): ViewModel() {

  private val _uiState = MutableStateFlow(UiState())

  val uiState = _uiState.asStateFlow()

  fun loadDiaryDetails(id: Int) {
    if (id == 0) {
      _uiState.update { current ->
        current.copy(
          diary = prepareEmptyDiary()
        )
      }
    } else {
      viewModelScope.launch {
        repository.loadDetails(id)?.let {
          _uiState.update { current ->
            current.copy(
              selectedDiaryId = id,
              diary = it,
            )
          }
        }
      }
    }
  }

  fun saveDiary() {
    viewModelScope.launch {
      repository.saveDiary(_uiState.value.diary)
    }
  }

  fun deleteDiary() {
    viewModelScope.launch {
      repository.deleteDiary(_uiState.value.diary)
    }
  }

  fun setTitle(title: String) {
    _uiState.update { current ->
      current.copy(
        diary = _uiState.value.diary.copy(
          title = title
        )
      )
    }
  }

  fun setDescription(description: String) {
    _uiState.update { current ->
      current.copy(
        diary = _uiState.value.diary.copy(
          description = description
        )
      )
    }
  }

  fun setMood(mood: Mood) {
    _uiState.update { current ->
      current.copy(
        diary = _uiState.value.diary.copy(
          mood = mood
        )
      )
    }
  }

  fun setGalleryImages(galleryImages: List<GalleryImage>) {
    val images = galleryImages.map { it.image.toString() }

    _uiState.update { current ->
      current.copy(
        diary = _uiState.value.diary.copy(
          images = images
        )
      )
    }
  }
}

private fun prepareEmptyDiary() =
  Diary(
    id = 0,
    title = "",
    description = "",
    mood = Mood.Neutral,
    images = emptyList(),
    date = LocalDateTime.now()
  )
