package com.behnamuix.spygame.feature.configrole.domain.usecase

import com.behnamuix.spygame.feature.configgame.domain.repository.ConfigGameRepository
import com.behnamuix.spygame.feature.configrole.domain.model.Player
import com.behnamuix.spygame.feature.configrole.domain.repository.ConfigRoleRepository
import javax.inject.Inject

class ConfigRoleUseCase @Inject constructor(
    private val configRoleRepo: ConfigRoleRepository
) {
    fun configRole(word:String): MutableList<Player>{
        return configRoleRepo.configRole(word)
    }
}