package com.behnamuix.spygame.core.di

import android.content.Context
import androidx.room.Room
import com.behnamuix.spygame.core.database.SpyDatabase
import com.behnamuix.spygame.feature.configword.data.local.KeywordDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): SpyDatabase =
        Room.databaseBuilder(
            context,
            SpyDatabase::class.java,
            "spy.db"
        ).build()

    @Provides
    fun provideKeywordDao(database: SpyDatabase): KeywordDao =
        database.keyWordDao()

}