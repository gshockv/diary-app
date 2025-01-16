package com.gshockv.dairyapp.data

import com.gshockv.dairyapp.util.RequestState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject

typealias Diaries = RequestState<Map<LocalDate, List<Diary>>>

class DiaryRepository @Inject constructor(private val diaryDao: DiaryDao) {

  fun observeAll(): Flow<Diaries> {
    return flowOf(testDiaries())
      .map { diary ->
        diary.groupBy { it.date.toLocalDate() }
      }
      .map {
        RequestState.Success(data = it)
      }

    //return diaryDao.observeAll()
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
    date = LocalDateTime.now()
  ),
  Diary(
    id = 2,
    title = "Diary 12",
    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod " +
        "tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis " +
        "nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis ",
    mood = Mood.Angry,
    images = listOf(),
    date = LocalDateTime.now()
  ),
  Diary(
    id = 3,
    title = "Diary 3",
    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod " +
        "tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis " +
        "nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis ",
    mood = Mood.Calm,
    images = listOf(),
    date = LocalDateTime.now()
  ),
  Diary(
    id = 4,
    title = "Diary 4",
    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod " +
        "tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis " +
        "nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis ",
    mood = Mood.Happy,
    images = listOf(),
    date = LocalDateTime.now().minusDays(1)
  ),
  Diary(
    id = 5,
    title = "Diary 5",
    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod " +
        "tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis " +
        "nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis ",
    mood = Mood.Mysterious,
    images = listOf(),
    date = LocalDateTime.now().minusDays(2)
  ),
  Diary(
    id = 6,
    title = "Diary 6",
    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod " +
        "tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis " +
        "nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis ",
    mood = Mood.Lonely,
    images = listOf(),
    date = LocalDateTime.now().minusDays(2)
  ),
  Diary(
    id = 7,
    title = "Diary 7",
    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod " +
        "tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis " +
        "nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis ",
    mood = Mood.Romantic,
    images = listOf(),
    date = LocalDateTime.now().minusDays(2)
  ),
  Diary(
    id = 8,
    title = "Diary 8",
    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod " +
        "tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis " +
        "nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis ",
    mood = Mood.Depressed,
    images = listOf(),
    date = LocalDateTime.now().minusDays(3)
  ),
  Diary(
    id = 9,
    title = "Diary 9",
    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod " +
        "tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis " +
        "nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis ",
    mood = Mood.Tense,
    images = listOf(),
    date = LocalDateTime.now().minusDays(3)
  ),
  Diary(
    id = 10,
    title = "Diary 10",
    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod " +
        "tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis " +
        "nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis ",
    mood = Mood.Calm,
    images = listOf(),
    date = LocalDateTime.now().minusDays(4)
  ),
  Diary(
    id = 11,
    title = "Diary 11",
    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod " +
        "tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis " +
        "nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis ",
    mood = Mood.Disappointed,
    images = listOf(),
    date = LocalDateTime.now().minusDays(4)
  ),
  Diary(
    id = 12,
    title = "Diary 12",
    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod " +
        "tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis " +
        "nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis ",
    mood = Mood.Happy,
    images = listOf(),
    date = LocalDateTime.now().minusDays(4)
  ),
  Diary(
    id = 13,
    title = "Diary 13",
    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod " +
        "tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis " +
        "nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis ",
    mood = Mood.Romantic,
    images = listOf(),
    date = LocalDateTime.now().minusDays(5)
  ),
  Diary(
    id = 14,
    title = "Diary 14",
    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod " +
        "tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis " +
        "nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis ",
    mood = Mood.Shameful,
    images = listOf(),
    date = LocalDateTime.now().minusDays(5)
  ),
  Diary(
    id = 15,
    title = "Diary 15",
    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod " +
        "tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis " +
        "nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis ",
    mood = Mood.Shameful,
    images = listOf(),
    date = LocalDateTime.now().minusDays(10)
  )
)
