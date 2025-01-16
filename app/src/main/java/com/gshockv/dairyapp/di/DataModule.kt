package com.gshockv.dairyapp.di

import android.content.Context
import androidx.room.Room
import com.gshockv.dairyapp.data.DiaryDao
import com.gshockv.dairyapp.data.DiaryDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

  @Singleton
  @Provides
  fun provideDatabase(@ApplicationContext context: Context): DiaryDatabase {
    return Room.databaseBuilder(
      context.applicationContext,
      DiaryDatabase::class.java,
      "Diary.db"
    ).build()
  }

  @Provides
  fun provideDiaryDao(db: DiaryDatabase): DiaryDao = db.diaryDao()
}
