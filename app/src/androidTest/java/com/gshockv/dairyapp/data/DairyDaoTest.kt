package com.gshockv.dairyapp.data

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.gshockv.dairyapp.model.Diary
import com.gshockv.dairyapp.model.Mood
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import java.time.LocalDateTime

class DairyDaoTest {
  private lateinit var database: DiaryDatabase

  @Before
  fun initDb() {
    database = Room.inMemoryDatabaseBuilder(
      getApplicationContext(),
      DiaryDatabase::class.java
    ).allowMainThreadQueries().build()
  }

  @Test
  fun initiallyDiaryIsEmpty() = runTest {
    assertEquals(0, database.diaryDao().observeAll().first().size)
  }

  @Test
  fun initiallyNoDairyCanBeLoadedById() = runTest {
    assertNull(database.diaryDao().load(diaryId = 42))
  }

  @Test
  fun insertDiaryAndGetAllRecords() = runTest {
    val diary = Diary(
      id = 1,
      title = "Test Diary",
      description = "Lorem ipsum...",
      date = LocalDateTime.now(),
      images = listOf("image1.png", "image2.png", "image3.png"),
      mood = Mood.Happy
    )

    database.diaryDao().upsert(diary)

    val records = database.diaryDao().observeAll().first()

    assertEquals(1, records.size)
    assertEquals(diary, records[0])
  }

  @Test
  fun insertDiaryAndLoadIt() = runTest {
    val diary = Diary(
      id = 1,
      title = "Test Diary",
      description = "Lorem ipsum...",
      date = LocalDateTime.now(),
      images = listOf("image1.png", "image2.png", "image3.png"),
      mood = Mood.Happy
    )
    database.diaryDao().upsert(diary)

    val loaded = database.diaryDao().load(diaryId = diary.id)
    assertNotNull(loaded)
    assertEquals(diary, loaded)
  }

  @Test
  fun testUpdateDiary() = runTest {
    val diary = Diary(
      id = 1,
      title = "Test Diary",
      description = "Lorem ipsum...",
      date = LocalDateTime.now(),
      images = listOf("image1.png", "image2.png", "image3.png"),
      mood = Mood.Happy
    )
    database.diaryDao().upsert(diary)

    val update = diary.copy(
      title = "Updated...",
      images = listOf("foo.png")
    )
    database.diaryDao().upsert(update)

    val fromDb = database.diaryDao().load(diary.id)
    assertEquals(update, fromDb)
  }

  @Test
  fun deleteDiaryRecord() = runTest {
    val diary = Diary(
      id = 1,
      title = "Test Diary",
      description = "Lorem ipsum...",
      date = LocalDateTime.now(),
      images = listOf("image1.png", "image2.png", "image3.png"),
      mood = Mood.Happy
    )
    database.diaryDao().upsert(diary)
    assertNotNull(database.diaryDao().load(diaryId = diary.id))

    database.diaryDao().delete(diary)
    assertNull(database.diaryDao().load(diaryId = diary.id))
  }
}
