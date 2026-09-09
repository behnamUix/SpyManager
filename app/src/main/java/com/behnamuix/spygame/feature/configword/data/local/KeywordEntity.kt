package com.behnamuix.spygame.feature.configword.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "words")
data class KeywordEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
    val word: String,
)