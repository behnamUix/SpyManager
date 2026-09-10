package com.behnamuix.spygame.utils



fun shuffledList(useSecureRandom: Boolean, list: MutableList<Player>): MutableList<Player> {
    return if (useSecureRandom) {
        list.shuffled(java.security.SecureRandom())
    } else {
        list.shuffled()
    }.toMutableList()

}


data class Player(var id: Int, var role: String)
