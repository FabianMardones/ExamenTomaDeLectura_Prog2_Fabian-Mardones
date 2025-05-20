package com.example.examentomadelectura.di

import android.app.Application
import androidx.room.Room
import com.example.examentomadelectura.data.DataRepository
import com.example.examentomadelectura.data.local.AppDatabase
import com.example.examentomadelectura.data.local.ReadingDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            AppDatabase::class.java,
            "readings_database"
        )
            .build()
    }

    @Provides
    fun provideReadingDao(appDatabase: AppDatabase): ReadingDao {
        return appDatabase.readingDao()
    }

    @Provides
    @Singleton
    fun provideDataRepository(readingDao: ReadingDao): DataRepository {
        return DataRepository(readingDao)
    }
}