package com.behnamuix.spygame.feature.configgame.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.behnamuix.spygame.feature.configgame.domain.usecase.ConfigGameUseCase
import com.behnamuix.spygame.feature.configgame.presentation.contract.ConfigGameContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class ConfigGameViewModel @Inject constructor(
    private val useCase: ConfigGameUseCase
) : ViewModel() {
    val listTrack = listOf(
        "https://dl.songsara.net/FRE/2022/6/Gabriel%20Saban%20-%20Ambition%20-%20Political%20Orchestral%20Drama%20(2022)%20SONGSARA.NET/01%20A%20Crucial%20Moment.mp3",
        "https://dl.songsara.net/FRE/2022/6/Gabriel%20Saban%20-%20Ambition%20-%20Political%20Orchestral%20Drama%20(2022)%20SONGSARA.NET/04%20Political%20Tactics.mp3",
        "https://dl.songsara.net/FRE/2022/6/Gabriel%20Saban%20-%20Ambition%20-%20Political%20Orchestral%20Drama%20(2022)%20SONGSARA.NET/02%20Great%20Women.mp3"
    )
    private val _configGameState =
        MutableStateFlow(ConfigGameContract.ConfigGameState())

    val configGameState: StateFlow<ConfigGameContract.ConfigGameState> =
        _configGameState.asStateFlow()

    fun onAction(action: ConfigGameContract.ConfigGameAction) {
        when (action) {
            is ConfigGameContract.ConfigGameAction.setSetAiSwitch -> setAiSwitch(action.value)
            is ConfigGameContract.ConfigGameAction.setBiometricProgress -> getBiometricProg()
            is ConfigGameContract.ConfigGameAction.SetEnabled ->
                setEnabled(action.enabled)


            ConfigGameContract.ConfigGameAction.IncreaseAgentCount ->
                incAgentCountPlayer()

            ConfigGameContract.ConfigGameAction.DecreaseAgentCount ->
                decAgentCountPlayer()

            ConfigGameContract.ConfigGameAction.IncreaseSpyCount ->
                incSpyCountPlayer()

            ConfigGameContract.ConfigGameAction.DecreaseSpyCount ->
                decSpyCountPlayer()


            is ConfigGameContract.ConfigGameAction.Initialize ->
                init(action.agentCount, action.spyCount)

            /*  ConfigGameContract.ConfigGameAction.PlayMusic ->
                  play()

              ConfigGameContract.ConfigGameAction.PauseMusic ->
                  pause()

              ConfigGameContract.ConfigGameAction.SetMusicVolume ->
                  setVolume()*/


            is ConfigGameContract.ConfigGameAction.SetProgress ->
                updateState { copy(progress = action.value) }
        }
    }

    private fun setAiSwitch(value: Boolean) {
        _configGameState.update { it.copy(useAi = value) }
    }

    private fun updateState(
        block: ConfigGameContract.ConfigGameState.() ->
        ConfigGameContract.ConfigGameState
    ) {
        _configGameState.update(block)
    }

    fun setEnabled(value: Boolean) {
        updateState { copy(enabled = value) }
    }


    fun getBiometricProg() {
        viewModelScope.launch {
            while (true) {
                delay(5.seconds)
                _configGameState.update {
                    it.copy(biometricSyncProg = useCase.getBiometricProg())
                }
            }
        }


    }

    fun incAgentCountPlayer() {
        if (configGameState.value.agentCount >= 10) return

        val count = useCase.incAgentCountPlayer()
        val code = useCase.getAgentCode()

        updateState {
            copy(
                agentCount = count,
                agentCode = code
            )
        }

        if (count > 5) {
            incSpyCountPlayer()
        }
    }

    fun decAgentCountPlayer() {
        if (configGameState.value.agentCount <= 2) return

        val count = useCase.decAgentCountPlayer()
        val code = useCase.getAgentCode()

        updateState {
            copy(
                agentCount = count,
                agentCode = code
            )
        }

        if (count < 5) {
            decSpyCountPlayer()
        }
    }

    fun incSpyCountPlayer() {
        if (configGameState.value.spyCount >= 3) return

        val count = useCase.incSpyCountPlayer()
        val code = useCase.getSpyCode()

        updateState {
            copy(
                spyCount = count,
                spyCode = code
            )
        }
    }

    fun decSpyCountPlayer() {
        if (configGameState.value.spyCount <= 1) return

        val count = useCase.decSpyCountPlayer()
        val code = useCase.getSpyCode()

        updateState {
            copy(
                spyCount = count,
                spyCode = code
            )
        }
    }


    fun init(agent: Int, spy: Int) {
        updateState {
            copy(
                agentCount = agent,
                spyCount = spy,
                agentCode = useCase.getAgentCode(),
                spyCode = useCase.getSpyCode()
            )
        }
    }


    /* fun play() {
         controller.play(listTrack.random())
     }

     fun pause() {
         controller.pause()
     }

     fun setVolume() {
         controller.setVolume(1f)
     }*/
}