package com.behnamuix.spygame.feature.configrole.presentation.contract

import com.behnamuix.spygame.feature.configrole.domain.model.Player
import com.behnamuix.spygame.feature.configword.domain.model.KeyWord
import com.behnamuix.spygame.feature.configword.presentation.contract.UiAction
import java.util.Collections.emptyList


object ConfigRoleContract {
    data class ConfigRoleState(
        val playerList: List<Player> = emptyList()
    )
    sealed class ConfigRoleAction{
        data object configRole: ConfigRoleAction()
    }


}