package com.behnamuix.spygame.feature.configrole.data.local

import com.behnamuix.spygame.feature.configgame.domain.model.Agent
import com.behnamuix.spygame.feature.configgame.domain.model.Spy
import com.behnamuix.spygame.feature.configrole.domain.model.Player
import javax.inject.Inject

class ConfigRoleDataSource @Inject constructor() {


    val players = mutableListOf<Player>()
    fun configRoleLogic(useSecureRandom: Boolean,word:String): MutableList<Player> {

        var id = 1

        repeat(Agent.count) {
            players.add(
                Player(
                    id++,
                    "تو الان یه ماموری",
                    word
                )
            )
        }

        repeat(Spy.count) {
            players.add(
                Player(
                    id++,
                    "تو یه جاسوسی",
                    null
                )
            )
        }

        val shuffled =
            shuffledList(useSecureRandom, players)
        return shuffled

    }

    private fun shuffledList(
        useSecureRandom: Boolean,
        list: MutableList<Player>
    ): MutableList<Player> {
        return if (useSecureRandom) {
            list.shuffled(java.security.SecureRandom())
        } else {
            list.shuffled()
        }.toMutableList()

    }

}