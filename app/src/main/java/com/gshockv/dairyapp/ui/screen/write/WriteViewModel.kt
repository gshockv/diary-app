package com.gshockv.dairyapp.ui.screen.write

import androidx.lifecycle.ViewModel
import com.gshockv.dairyapp.data.DiaryRepository
import com.gshockv.dairyapp.data.Mood
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

data class UiState(
  val selectedDiaryId: Int = 0,
  val title: String = "",
  val description: String = "",
  val mood: Mood = Mood.Neutral
)

@HiltViewModel
class WriteViewModel @Inject constructor(
  private val repository: DiaryRepository
): ViewModel() {
  private val _uiState = MutableStateFlow(
    UiState(

    )
  )
  val uiState = _uiState.asStateFlow()

  //private val _diaryId

  fun setDiaryId(id: Int) {

  }


}
