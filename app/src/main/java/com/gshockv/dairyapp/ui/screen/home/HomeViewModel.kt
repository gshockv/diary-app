package com.gshockv.dairyapp.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gshockv.dairyapp.data.Diaries
import com.gshockv.dairyapp.data.Diary
import com.gshockv.dairyapp.data.DiaryRepository
import com.gshockv.dairyapp.util.RequestState
import com.gshockv.dairyapp.util.WhileUiSubscribed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
  private val repository: DiaryRepository
) : ViewModel() {

  val diariesState: StateFlow<Diaries> = repository.observeAll()
    .stateIn(
      scope = viewModelScope,
      started = WhileUiSubscribed,
      initialValue = RequestState.Idle
    )
}
