package com.gshockv.dairyapp.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gshockv.dairyapp.data.Diary
import com.gshockv.dairyapp.data.DiaryRepository
import com.gshockv.dairyapp.util.WhileUiSubscribed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import java.time.OffsetDateTime
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
  private val repository: DiaryRepository
) : ViewModel() {

  val uiState: StateFlow<Map<OffsetDateTime, List<Diary>>> = repository.observeAll()
    .map { records ->
      records.groupBy { record -> record.date }
    }
    .stateIn(
      scope = viewModelScope,
      started = WhileUiSubscribed,
      initialValue = emptyMap()
    )
}
