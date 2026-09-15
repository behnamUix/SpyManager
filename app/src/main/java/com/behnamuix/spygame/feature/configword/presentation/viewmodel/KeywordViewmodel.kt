package com.behnamuix.spygame.feature.configword.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.behnamuix.spygame.feature.configword.domain.model.KeyWord
import com.behnamuix.spygame.feature.configword.domain.usecase.KeyWordUseCase
import com.behnamuix.spygame.feature.configword.presentation.contract.UiAction
import com.behnamuix.spygame.feature.configword.presentation.contract.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class KeywordViewmodel @Inject constructor(
    private val keyWordUseCase: KeyWordUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    var uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun onAction(action: UiAction) {
        when (action) {
            is UiAction.GetKeyword -> getKeyword()
            is UiAction.AddKeyword -> addKeyword(action.word)
            is UiAction.DeleteKeyWord -> deleteKeyword(action.word)
            is UiAction.SetText -> setText(action.word)
            is UiAction.DeleteAll -> deleteAll()
        }
    }

    private fun deleteAll() {
        viewModelScope.launch {
            keyWordUseCase.deletedAll()
            getKeyword()
        }

    }

    private fun setText(word: String) {
        _uiState.update { it.copy(word = word) }
    }

    private fun deleteKeyword(word: KeyWord) {
        viewModelScope.launch {
            keyWordUseCase.deleteKeywords(word)


            getKeyword()
        }
    }

    private fun addKeyword(word: KeyWord) {
        viewModelScope.launch {
            keyWordUseCase.addKeywords(word)
            getKeyword()

        }
    }

    private fun getKeyword() {
        viewModelScope.launch {

            _uiState.update { it.copy(keyWords = keyWordUseCase.getKeywords()) }

        }
    }

}