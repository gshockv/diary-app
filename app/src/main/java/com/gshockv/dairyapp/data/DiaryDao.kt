package com.gshockv.dairyapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface DiaryDao {
  @Query("SELECT * FROM diary")
  fun observeAll(): Flow<List<Diary>>

  @Query("SELECT * FROM diary WHERE id = :diaryId")
  suspend fun load(diaryId: Int): Diary?

  @Upsert
  suspend fun upsert(diary: Diary)

  @Delete
  suspend fun delete(diary: Diary)
}
