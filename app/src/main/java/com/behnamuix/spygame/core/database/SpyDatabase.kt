package com.behnamuix.spygame.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.behnamuix.spygame.feature.configword.data.local.KeywordDao
import com.behnamuix.spygame.feature.configword.data.local.KeywordEntity

@Database(entities = [KeywordEntity::class], version = 2, exportSchema = false)
abstract class SpyDatabase : RoomDatabase() {
    abstract fun keyWordDao(): KeywordDao

}

