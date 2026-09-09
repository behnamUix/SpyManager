package com.behnamuix.spygame.feature.configword.data.mapper

import com.behnamuix.spygame.feature.configword.data.local.KeywordEntity
import com.behnamuix.spygame.feature.configword.domain.model.KeyWord


fun KeywordEntity.toKeyword(): KeyWord {
    return KeyWord(
        id = id,
        word = word
    )
}

fun KeyWord.toKeywordEntity(): KeywordEntity {
    return KeywordEntity(
        id = id,
        word = word
    )
}


