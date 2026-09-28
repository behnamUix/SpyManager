package com.behnamuix.spygame.feature.configrole.data.repository

import com.behnamuix.spygame.feature.configrole.data.local.ConfigRoleDataSource
import com.behnamuix.spygame.feature.configrole.data.local.ConfigTimeDataSource
import com.behnamuix.spygame.feature.configrole.domain.model.Player
import com.behnamuix.spygame.feature.configrole.domain.repository.ConfigRoleRepository
import com.behnamuix.spygame.feature.configrole.domain.repository.ConfigTimeRepository
import javax.inject.Inject

class ConfigTimeRepositoryImpl @Inject constructor(private val configTimeDataSource: ConfigTimeDataSource) :
    ConfigTimeRepository {
    override fun increaseTime():Int {
       return configTimeDataSource.increaseTime()
    }

    override fun decreaseTime():Int {
        return  configTimeDataSource.decreaseTime()
    }


}