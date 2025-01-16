package com.gshockv.dairyapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.OffsetDateTime

@Entity(tableName = "diary")
data class Diary(
  @PrimaryKey(autoGenerate = true) val id: Int,
  val title: String,
  val description: String,
  val mood: Mood,
  val images: List<String>,
  val date: OffsetDateTime
)
