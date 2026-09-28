package com.behnamuix.spygame.feature.configrole.domain.repository

interface ConfigTimeRepository {
    fun increaseTime():Int
    fun decreaseTime():Int
}