package com.gshockv.dairyapp.data.converter

import com.gshockv.dairyapp.data.Mood
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class DataTypeConverterTest {
  private val underTest = DataTypeConverter()

  @Test
  fun testConvertToJson() {
    val list = listOf("image_1.png", "image_2.png", "image_3.png")
    val convertedList = underTest.fromStringList(list)

    assertEquals("""
      ["image_1.png","image_2.png","image_3.png"]
    """.trimIndent(), convertedList)
  }

  @Test
  fun testConvertFromJsonToList() {
    val json = """["image_1.png","image_2.png","image_3.png"]""".trimIndent()
    val list = underTest.toStringList(json)

    assertNotNull(list)
    assertEquals(3, list!!.size)

    assertEquals("image_1.png", list[0])
    assertEquals("image_2.png", list[1])
    assertEquals("image_3.png", list[2])
  }

  @Test
  fun testConvertMoodToString() {
    val converted = underTest.fromMood(Mood.Happy)
    assertEquals(Mood.Happy.name, converted)
  }

  @Test
  fun testConvertToMood() {
    val initial = Mood.Angry.name
    val converted = underTest.toMood(initial)

    assertEquals(Mood.Angry, converted)
  }
}
