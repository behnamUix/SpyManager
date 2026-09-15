package com.behnamuix.spygame.feature.configword.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface KeywordDao {
    @Query("SELECT * FROM words ORDER BY word ASC")
    suspend fun getAll(): MutableList<KeywordEntity>

    // 🔴 ورودی تابع از String به KeywordEntity اصلاح شد
    @Insert
    suspend fun insert(wordEntity: KeywordEntity)

    @Delete
    suspend fun delete(word: KeywordEntity)

    @Query("DELETE  FROM words")
    suspend fun deleteAll()
}