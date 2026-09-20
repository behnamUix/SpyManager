package com.behnamuix.spygame.feature.configgame.presentation.contract

import com.behnamuix.spygame.feature.configword.domain.model.KeyWord
import com.behnamuix.spygame.feature.configword.presentation.contract.UiAction


object ConfigGameContract {
    data class ConfigGameState(


        val agentCount: Int = 1,
        val spyCount: Int = 1,
        val agentCode: String = "",
        val spyCode: String = "",
        val enabled: Boolean = true,
        val progress: Boolean = true,
        val biometricSyncProg:Float=0f,
        val useAi: Boolean=false
    )

    sealed class ConfigGameAction {
        data class setSetAiSwitch(val value: Boolean): ConfigGameAction()

        data class SetEnabled(val enabled: Boolean) : ConfigGameAction()



        data object IncreaseAgentCount : ConfigGameAction()
        data object DecreaseAgentCount : ConfigGameAction()
        data object IncreaseSpyCount : ConfigGameAction()
        data object DecreaseSpyCount : ConfigGameAction()



        data class Initialize(
            val agentCount: Int,
            val spyCount: Int
        ) : ConfigGameAction()

/*
        data object PlayMusic : ConfigGameAction()
        data object PauseMusic : ConfigGameAction()
        data object SetMusicVolume : ConfigGameAction()
*/



        data object setBiometricProgress: ConfigGameAction()

        data class SetProgress(val value: Boolean) : ConfigGameAction()
    }

}