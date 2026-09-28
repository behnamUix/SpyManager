package com.behnamuix.spygame.feature.configrole.domain.usecase

import com.behnamuix.spygame.feature.configgame.domain.repository.ConfigGameRepository
import com.behnamuix.spygame.feature.configrole.domain.model.Player
import com.behnamuix.spygame.feature.configrole.domain.repository.ConfigRoleRepository
import com.behnamuix.spygame.feature.configrole.domain.repository.ConfigTimeRepository
import javax.inject.Inject

class ConfigTimeUseCase @Inject constructor(
    private val configTimeRepo: ConfigTimeRepository
) {
    fun increaseTime():Int{
        return configTimeRepo.increaseTime()
    }
    fun decreaseTime():Int{
      return  configTimeRepo.decreaseTime()
    }
}