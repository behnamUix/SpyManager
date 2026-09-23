package com.behnamuix.spygame.feature.configrole.domain.repository

import com.behnamuix.spygame.feature.configrole.domain.model.Player

interface ConfigRoleRepository {
    fun configRole(word: String): MutableList<Player>
}