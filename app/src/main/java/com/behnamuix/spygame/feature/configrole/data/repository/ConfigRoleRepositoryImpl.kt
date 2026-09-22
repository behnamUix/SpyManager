package com.behnamuix.spygame.feature.configrole.data.repository

import com.behnamuix.spygame.feature.configrole.data.local.ConfigRoleDataSource
import com.behnamuix.spygame.feature.configrole.domain.model.Player
import com.behnamuix.spygame.feature.configrole.domain.repository.ConfigRoleRepository
import javax.inject.Inject

class ConfigRoleRepositoryImpl @Inject constructor(private val configRoleDataSource: ConfigRoleDataSource) :
    ConfigRoleRepository {
    override fun configRole(): MutableList<Player> {
        return configRoleDataSource.configRoleLogic(true)
    }

}