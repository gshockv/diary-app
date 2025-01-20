package com.gshockv.dairyapp.data

import com.gshockv.dairyapp.util.RequestState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import javax.inject.Inject

typealias Diaries = RequestState<Map<LocalDate, List<Diary>>>

class DiaryRepository @Inject constructor(private val diaryDao: DiaryDao) {

  fun observeAll(): Flow<Diaries> {
    return diaryDao.observeAll()
      .map { diary ->
        diary.groupBy { it.date.toLocalDate() }
      }
      .map {
        RequestState.Success(data = it)
      }
  }

  suspend fun loadDetails(id: Int): Diary? {
    return diaryDao.load(diaryId = id)
  }

  suspend fun saveDiary(diary: Diary) {
    diaryDao.upsert(diary)
  }

  suspend fun deleteDiary(diary: Diary) {
    diaryDao.delete(diary)
  }
}
