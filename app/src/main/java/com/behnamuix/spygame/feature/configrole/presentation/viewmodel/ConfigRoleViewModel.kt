package com.behnamuix.spygame.feature.configrole.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.behnamuix.spygame.feature.configgame.presentation.contract.ConfigGameContract
import com.behnamuix.spygame.feature.configrole.domain.usecase.ConfigRoleUseCase
import com.behnamuix.spygame.feature.configrole.presentation.contract.ConfigRoleContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConfigRoleViewModel @Inject constructor(private val configRoleUseCase: ConfigRoleUseCase) :
    ViewModel() {
    private val _configRoleState =
        MutableStateFlow(ConfigRoleContract.ConfigRoleState())

    val configRoleState: StateFlow<ConfigRoleContract.ConfigRoleState> =
        _configRoleState.asStateFlow()

    fun onAction(action: ConfigRoleContract.ConfigRoleAction) {
        when (action) {
            is ConfigRoleContract.ConfigRoleAction.configRole -> configRole()
        }
    }

    private fun configRole() {
        viewModelScope.launch {
            _configRoleState.update { it.copy(playerList = configRoleUseCase.configRole()) }
        }
    }
}