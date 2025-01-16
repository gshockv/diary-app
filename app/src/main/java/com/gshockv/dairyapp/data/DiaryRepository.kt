package com.gshockv.dairyapp.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import java.time.OffsetDateTime
import javax.inject.Inject

class DiaryRepository @Inject constructor(private val diaryDao: DiaryDao) {

  fun observeAll(): Flow<List<Diary>> {
    //return diaryDao.observeAll()
    return MutableStateFlow(testDiaries())
  }
}

private fun testDiaries() = listOf(
  Diary(
    id = 1,
    title = "Diary 1",
    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod " +
        "tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis " +
        "nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis ",
    mood = Mood.Happy,
    images = listOf(),
    date = OffsetDateTime.now()
  ),
  Diary(
    id = 2,
    title = "Diary 12",
    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod " +
        "tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis " +
        "nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis ",
    mood = Mood.Angry,
    images = listOf(),
    date = OffsetDateTime.now()
  ),
  Diary(
    id = 3,
    title = "Diary 3",
    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod " +
        "tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis " +
        "nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis ",
    mood = Mood.Calm,
    images = listOf(),
    date = OffsetDateTime.now()
  ),
  Diary(
    id = 4,
    title = "Diary 4",
    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod " +
        "tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis " +
        "nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis ",
    mood = Mood.Happy,
    images = listOf(),
    date = OffsetDateTime.now().minusDays(1)
  ),
  Diary(
    id = 5,
    title = "Diary 5",
    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod " +
        "tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis " +
        "nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis ",
    mood = Mood.Mysterious,
    images = listOf(),
    date = OffsetDateTime.now().minusDays(2)
  ),
  Diary(
    id = 6,
    title = "Diary 6",
    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod " +
        "tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis " +
        "nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis ",
    mood = Mood.Lonely,
    images = listOf(),
    date = OffsetDateTime.now().minusDays(2)
  ),
  Diary(
    id = 7,
    title = "Diary 7",
    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod " +
        "tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis " +
        "nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis ",
    mood = Mood.Romantic,
    images = listOf(),
    date = OffsetDateTime.now().minusDays(2)
  ),
  Diary(
    id = 8,
    title = "Diary 8",
    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod " +
        "tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis " +
        "nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis ",
    mood = Mood.Depressed,
    images = listOf(),
    date = OffsetDateTime.now().minusDays(3)
  ),
  Diary(
    id = 9,
    title = "Diary 9",
    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod " +
        "tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis " +
        "nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis ",
    mood = Mood.Tense,
    images = listOf(),
    date = OffsetDateTime.now().minusDays(3)
  ),
  Diary(
    id = 10,
    title = "Diary 10",
    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod " +
        "tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis " +
        "nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis ",
    mood = Mood.Calm,
    images = listOf(),
    date = OffsetDateTime.now().minusDays(4)
  ),
  Diary(
    id = 11,
    title = "Diary 11",
    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod " +
        "tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis " +
        "nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis ",
    mood = Mood.Disappointed,
    images = listOf(),
    date = OffsetDateTime.now().minusDays(4)
  ),
  Diary(
    id = 12,
    title = "Diary 12",
    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod " +
        "tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis " +
        "nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis ",
    mood = Mood.Happy,
    images = listOf(),
    date = OffsetDateTime.now().minusDays(4)
  ),
  Diary(
    id = 13,
    title = "Diary 13",
    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod " +
        "tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis " +
        "nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis ",
    mood = Mood.Romantic,
    images = listOf(),
    date = OffsetDateTime.now().minusDays(5)
  ),
  Diary(
    id = 14,
    title = "Diary 14",
    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod " +
        "tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis " +
        "nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis ",
    mood = Mood.Shameful,
    images = listOf(),
    date = OffsetDateTime.now().minusDays(5)
  ),
  Diary(
    id = 15,
    title = "Diary 15",
    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod " +
        "tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis " +
        "nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis ",
    mood = Mood.Shameful,
    images = listOf(),
    date = OffsetDateTime.now().minusDays(10)
  )
)
