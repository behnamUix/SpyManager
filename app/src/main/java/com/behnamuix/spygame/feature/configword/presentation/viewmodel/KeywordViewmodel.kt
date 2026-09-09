package com.behnamuix.spygame.feature.configword.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.behnamuix.spygame.feature.configword.domain.usecase.KeyWordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class KeywordViewmodel @Inject constructor(
    private val keyWordUseCase: KeyWordUseCase
) : ViewModel() {
    init {
        runBlocking {

            keyWordUseCase.getKeywords()
        }
    }
}