package com.gshockv.dairyapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gshockv.dairyapp.data.converter.DataTypeConverter

@Database(
  entities = [Diary::class],
  version = 1,
  exportSchema = false
)
@TypeConverters(DataTypeConverter::class)
abstract class DiaryDatabase: RoomDatabase() {
  abstract fun diaryDao(): DiaryDao
}
