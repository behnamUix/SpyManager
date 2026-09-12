package com.behnamuix.spygame.feature.configword.presentation.contract

import com.behnamuix.spygame.feature.configword.domain.model.KeyWord

data class UiState(
    var word: String = "",
    var keyWords: List<KeyWord> = emptyList()
)

sealed interface UiAction {
    data class DeleteKeyWord(val word: KeyWord) : UiAction
    data class AddKeyword(val word: KeyWord) : UiAction
    data object GetKeyword : UiAction

    data class SetText(val word:String): UiAction

}