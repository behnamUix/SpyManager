package com.behnamuix.spygame.feature.configword.data.repository

import com.behnamuix.spygame.feature.configword.data.mapper.toKeyword
import com.behnamuix.spygame.feature.configword.data.local.KeywordDao
import com.behnamuix.spygame.feature.configword.data.mapper.toKeywordEntity
import com.behnamuix.spygame.feature.configword.domain.model.KeyWord
import com.behnamuix.spygame.feature.configword.domain.repository.KeywordRepository
import javax.inject.Inject

class KeywordRepositoryImpl @Inject constructor(
    private val keywordDao: KeywordDao
): KeywordRepository {
    override suspend fun getKeywords(): List<KeyWord> {
        return keywordDao.getAll().map {
            it.toKeyword()
        }
    }

    override suspend fun addKeywords(word: KeyWord) {
        keywordDao.insert(word.toKeywordEntity())
    }

    override suspend fun deleteKeywords(word: KeyWord) {
        keywordDao.delete(word.toKeywordEntity())
    }

    override suspend fun deleteAll() {
        keywordDao.deleteAll()
    }
}