package com.behnamuix.spygame.feature.configword.domain.usecase

import com.behnamuix.spygame.feature.configword.domain.model.KeyWord
import com.behnamuix.spygame.feature.configword.domain.repository.KeywordRepository
import javax.inject.Inject

class KeyWordUseCase @Inject constructor(
    private val keyWordRepo: KeywordRepository
) {
    suspend fun getKeywords() = keyWordRepo.getKeywords()
    suspend fun addKeywords(word: KeyWord) = keyWordRepo.addKeywords(word)
    suspend fun deleteKeywords(word: KeyWord) = keyWordRepo.deleteKeywords(word)
}