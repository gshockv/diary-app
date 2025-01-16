package com.gshockv.dairyapp.data.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.gshockv.dairyapp.data.Mood
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

class DataTypeConverter {
  private val dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME

  @TypeConverter
  fun fromStringList(value: List<String>): String? {
    return Gson().toJson(value)
  }

  @TypeConverter
  fun toStringList(value: String?): List<String>? {
    val listType = object : TypeToken<List<String>>() {}.type
    return Gson().fromJson(value, listType)
  }

  @TypeConverter
  fun fromOffsetDateTimeToString(value: OffsetDateTime?): String? {
    return value?.format(dateTimeFormatter)
  }

  @TypeConverter
  fun toOffsetDateTime(value: String?): OffsetDateTime? {
    return value?.let {
      OffsetDateTime.parse(it, dateTimeFormatter)
    }
  }

  @TypeConverter
  fun fromMood(value: Mood?): String? {
    return value?.name
  }

  @TypeConverter
  fun toMood(value: String?): Mood? {
    return value?.let {
      Mood.valueOf(it)
    }
  }
}
