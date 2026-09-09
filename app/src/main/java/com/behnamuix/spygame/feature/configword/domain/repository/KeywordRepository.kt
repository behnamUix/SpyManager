package com.behnamuix.spygame.feature.configword.domain.repository

import com.behnamuix.spygame.feature.configword.domain.model.KeyWord


interface KeywordRepository {
    suspend fun getKeywords(): List<KeyWord>
    suspend fun addKeywords(word: KeyWord)
    suspend fun deleteKeywords(word: KeyWord)
}